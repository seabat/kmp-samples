import { onRequest } from "firebase-functions/v2/https"
import * as logger from "firebase-functions/logger"
import * as admin from "firebase-admin"
const serviceAccount = require("./service-account.json")

interface FetchRequestBody {
  data: {
    accountId: string
  }
}

interface CreateRequestBody {
  data: {
    accountId: string
    balance: number
  }
}

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://seabat-dev-default-rtdb.firebaseio.com/"
})

exports.fetchCustomToken = onRequest(
  {
    timeoutSeconds: 60,
    region: ["asia-northeast2"]
  },
  async (request, response) => {
    const body = request.body as FetchRequestBody

    logger.log(`accountId: ${body.data.accountId}`)

    if (typeof body?.data?.accountId !== "string") {
      console.log("accountId is not string")
      response.status(404).send({
        data: "error : accountId is not string"
      })
      return
    }

    const customToken = await admin.auth().createCustomToken(body.data.accountId)
    response.status(200).send({
      data: { customToken: customToken }
    })
  }
)

exports.createUserRecord = onRequest(
  {
    timeoutSeconds: 60,
    region: ["asia-northeast2"]
  },
  async (request, response) => {
    const body = request.body as CreateRequestBody

    logger.log(`accountId: ${body.data.accountId}, balance: ${body.data.balance}`)

    if (typeof body?.data?.accountId !== "string" || typeof body?.data?.balance !== "number") {
      console.log("Invalid request body")
      response.status(400).send({
        error: "accountId must be a string and balance must be a number"
      })
      return
    }

    try {
      await admin.database().ref(`users/${body.data.accountId}`).set({
        balance: body.data.balance
      })
      response.status(201).send({ message: "User record created successfully" }) // 201 Created を使用
    } catch (error) {
      console.error("Error creating user record:", error)
      response.status(500).send({ error: "Failed to create user record" })
    }
  }
)
