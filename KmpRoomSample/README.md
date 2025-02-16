
Kmp Multi Module
===============

### 概要

Kotlin Multiplatform の shared モジュールをマルチモジュール化したい際に参考になるリポジトリ。
[KmpTutorial](https://github.com/seabat/kmp-tutorial/tree/main/KmpTutorial)をベースにしている。

### マルチモジュール化

ViewModel 層、ドメイン層、データ層をそれぞれ Android、iOS の両 OS から利用できるモジュールに分割する。

<img src="KmpMultiModule/docs/module.png" width="300">

ポイントは Android、iOS の両 OS から依存するモジュールは一つだけに Umbrella モジュールを作ること。Umbrella モジュールを作る必要性については [ここ](https://santimattius.github.io/kmp-for-mobile-native-developers-book/#179504e6-f752-8099-8fa7-e8df8e7c661f)を参照されたい。


Kmp Realtime DataBase Sample
============================

Firebase Realtime Database にアクセスする Kotlin Multiplatform プロジェクトを作りたいときに参考になるリポジトリ。
[Qiita 記事](https://qiita.com/seabat-dev/items/f7a8a8097b4fd085cf63)のリファレンスコード。


Kmp Room Sample
===============

TBD

### プロジェクト作成手順

1. [KmpTutorial](https://github.com/seabat/kmp-tutorial/tree/main/KmpTutorial) プロジェクトをコピーする。  
2. ルートフォルダを KmpTutorial から KmpRoomSample に置き換える。
3. ディレクトリを dev/seabat/kmp/tutorial/ から dev/seabat/kmp/roomsample/ にリネーム。 
4. プロジェクト全体を "tutorial" で grep し、ヒットしたら "roomsample" に置き換える。
5. .idea/workspace.xml 内の "tutorial" を "roomsample" に置き換える。  
6. .idea/KmpTutorial.iml を .idea/KmpRoomSample.iml にリネーム。 
7. .idea/modules/composeApp/KmpTutorial.composeApp.iml を .idea/modules/composeApp/KmpRoomSample.composeApp.iml にリネーム。  
8. ビルドターゲット iosApp がエラーになっている場合は Run/Debug Configuratons を開き、 Excution Target を選択する。  
  <img src="docs/build_error_1.png" width="300">  <img src="docs/build_error_2.png" width="300">