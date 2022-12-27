# KeikaiLauncher

[<img src="https://f-droid.org/badge/get-it-on.png"
      alt="Get it on F-Droid"
      height="80">](https://f-droid.org/app/com.masterriceball.launcher)

*A fork of HayaiLauncher*
*A stripped down version of KeikaiLauncher*

This fork is not for most people. The launcher has bare mininal functionality for code simpicity and tiny size.

It is a fast, [free](https://en.wikipedia.org/wiki/Free_software), minimalist Android Launcher. Even though this fork is heavily modified this launcher is a tribute to the ideas and concepts of HayaiLauncher.


### Releasing a new version

1. Cut a new branch named `release/<version>`, where version should be the intended release version followng semantic
   versioning scheme, f.ex. `release/1.2.3`
2. In the release branch:
   * update the release version in the _app/build.gradle_ (do not forget to omit the `-SNAPSHOT` suffix)
     ```
     version "1.2.2"  // <- here
     android {
         defaultConfig {
             ...
             versionName "1.2.2" // <- and here
         }
         ...
     }
     ```
   * update `CHANGELOG.md`. Follow [keepachangelog](https://keepachangelog.com/en/1.0.0/) guidelines
   * create a changelog file under `fastlane/metadata/android/en-US/changelogs/` for the f-droid store
   * commit and push
3. Open a pull-request
4. Once the PR is merged, create an annotated tag on the merge commit with the version of the release
5. Finally, commit to _main_ (or open a PR) bumping a bugfix part of the version and adding `-SNAPSHOT` suffix to it

**NOTE:** the git tag must literally match the version specified in the `app/build.gradle`. This is required for the
_F-Droid_'s fastlane pipeline to pick up new versions of the application correctly.

