# KMP AppSync Sample

Kotlin Multiplatform (KMP) を使用した AWS AppSync のサンプルプログラム。

## 概要

このプロジェクトは、Kotlin Multiplatform を使用して Android と iOS の両方で動作するアプリケーションを開発するサンプルです。
AWS AppSync を使用してリアルタイムデータ同期を実現しています。

## プロジェクト構成

```
KmpAppSync/
├── composeApp/          # 共通コード
│   ├── commonMain/      # プラットフォーム共通のコード (TBD)
│   ├── androidMain/     # Android 固有のコード
│   └── iosMain/         # iOS 固有のコード
├── iosApp/              # iOS アプリケーション (TBD)
└── amplify/             # AWS Amplify 設定
```

## 機能

- ユーザー情報の表示
- ポイント残高の更新
- リアルタイムデータ同期
- マルチプラットフォーム対応（Android/iOS）
    ※ iOS は未対応です。

## セットアップ手順

### 1. 前提条件

- Kotlin 2.1.10 以上
- Android Studio Meerkat | 2024.3.1 以上
- Xcode 15.0 以上
- AWS アカウント
- AWS CLI のインストール

### 2. プロジェクトのクローン

```bash
git clone https://github.com/your-username/kmp-samples.git
cd kmp-samples/KmpAppSync
```

### 3. AWS Amplify の設定

1. AWS アカウントの設定
   - AWS CLI がインストールされていることを確認
   - 適切な認証情報が設定されていることを確認

2. プロジェクトの初期化
   ```bash
   amplify pull
   ```
   このコマンドにより、必要な設定ファイルが自動的に生成されます。

3. 機密情報の取り扱い
   - `amplify/team-provider-info.json`
   - `amplify/backend/api/**/cli-inputs.json`
   - `amplify/backend/api/**/parameters.json`
   これらのファイルには機密情報が含まれているため、Git リポジトリには含まれていません。
   `amplify pull` コマンドで必要な情報を取得してください。

### 4. ビルドと実行

#### Android
```bash
./gradlew :composeApp:androidDebug
```

#### iOS
```bash
./gradlew :composeApp:iosDebug
```

## アーキテクチャ

- **MVVM アーキテクチャ**を採用
- **Repository パターン**を使用してデータアクセスを抽象化
- **Kotlin Coroutines**を使用した非同期処理
- **AWS AppSync**を使用したリアルタイムデータ同期

## 使用技術

- Kotlin Multiplatform
- Jetpack Compose Multiplatform
- AWS Amplify
- AWS AppSync
- GraphQL
- DynamoDB

## ライセンス

このプロジェクトは MIT ライセンスの下で公開されています。
