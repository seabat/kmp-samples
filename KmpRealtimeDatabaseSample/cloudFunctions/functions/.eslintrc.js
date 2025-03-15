module.exports = {
  root: true,
  extends: ["eslint:recommended", "plugin:@typescript-eslint/recommended", "plugin:prettier/recommended"],
  plugins: ["@typescript-eslint", "import"],
  env: {
    node: true,
    es6: true
  },
  parser: "@typescript-eslint/parser",
  parserOptions: {
    project: ["tsconfig.json", "tsconfig.dev.json"],
    sourceType: "module"
  },
  ignorePatterns: [
    "/lib/**/*" // Ignore built files.
  ],
  rules: {
    "import/no-unresolved": 0,
    "@typescript-eslint/no-require-imports": "off",
    indent: ["error", 2]
  }
}
