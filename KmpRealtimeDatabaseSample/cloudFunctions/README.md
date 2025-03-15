# firebase-functions-v2-starter

Cloud Functions for Firebase V2(第２世代) の勉強用リポジトリ。

- 目的
    - Cloud Functions for Firebase V2 を使って Cloud Functions for Firebase 上に HTTP 経由でアクセスできる関数を作成
    - プログラム言語 TypeScript 
    - ESLint と Prettier を適用
    - ターミナルから HTTP 関数にアクセス

## 開発環境を構築

**[はじめに: 最初の関数の記述、テスト、デプロイ](https://firebase.google.com/docs/functions/get-started?hl=ja&authuser=0&gen=2nd)**
を参考に、以下を実行する。

1. Firebase プロジェクトを作成する
2. 開発PC に Node.js 環境を構築する
3. Firebase CLI をインストールする

## TypeScript のスケルトンプロジェクトを作成


[Cloud Functions に TypeScript を使用する](https://firebase.google.com/docs/functions/typescript?authuser=0&hl=ja) を参考にTypeScript のスケルトンプロジェクトを作成する。

ログインする。

```shell
$ firebase login
? Allow Firebase to collect CLI and Emulator Suite usage and error reporting information? Yes
・・・
✔  Success! Logged in as XXXX@gmail.com
```

スケルトンプロジェクトを作成する。

```shell
$ firebase init functions
```

「Use an existing project」を選択する。

```shell
? Please select an option: 
❯ Use an existing project 
  Create a new project 
  Add Firebase to an existing Google Cloud Platform project 
  Don't set up a default project
```

Firebase Console で作成したプロジェクト(ex.seabat-dev) を選択する。

```shell
? Select a default Firebase project for this directory: 
❯ seabat-dev (seabat-dev) 
```

TypeScript を選択する。

```shell
? What language would you like to use to write Cloud Functions? 
  JavaScript 
❯ TypeScript 
  Python
```

ESLint使用を選択する。

```shell
? Do you want to use ESLint to catch probable bugs and enforce style? Yes
```

## スケルトンプロジェクトに ESLint と Prettier を適用

本 git リポジトリをクローンし、ESLint と Prettier の設定箇所をコピーする。

    # git clone https://github.com/seabat/firebase-functions-v2-starter.git

### Visual Studio Code

Visual Studio Code に以下のパッケージをインストールする。
- [EditorConfig for VS Code](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig)
- [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
- [Prettier](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)

.vscode/setting.json を作成し、以下を記載する。

```
{
    //*.ts
    "[typescript]": {
      "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
      }
    },
    //*.tsx
    "[typescriptreact]": {
      "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
      }
    }
}
```

## 最初のコード

以下を functions/src/index.ts に貼り付ける。

```typescript
import { onRequest } from "firebase-functions/v2/https"
import * as logger from "firebase-functions/logger"

exports.sayHelloV2 = onRequest(
  {
    timeoutSeconds: 60,
    region: ["asia-northeast2"]
  },
  (req, res) => {
    logger.log("Hello!! V2")
    res.status(200).send("Hello V2 world!")
  }
)
```

* 注意
    - `region:` には Firebase Console で設定したリージョンを設定する。

## Lint とフォーマットの実行

Lint を実行する。

```shell
$ npm run lint
```

フォーマットを実行する。

```shell
$ npm run lint:fix
```

## ビルド

```shell
$ npm run build
```

## デプロイ

`firebase use` でデプロイ先のプロジェクトを確認してから `firebase deploy` でデプロイする。

```shell
$ firebase use
* default (seabat-dev)

$ firebase deploy --only functions:sayHelloV2
・・・
✔  functions[sayHello(asia-northeast2)] Successful create operation.
Function URL (sayHello(asia-northeast2)): https://sayhello-XXXX.a.run.app
i  functions: cleaning up build files...
```

## ターミナルから HTTP 関数にアクセスする

デプロイ時に表示された URL にアクセスする。

```shell
$ curl https://sayhello-XXXX.a.run.app
Hello world!%
```