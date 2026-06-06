# GitHub Actions CI/CD Setup Guide

This guide explains how to set up GitHub Actions to automatically build and sign your Android APK.

## 🔑 Step 1: Add GitHub Secrets

GitHub Actions needs secure access to your keystore credentials. Follow these steps:

1. Go to your repository on GitHub
2. Navigate to **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret** and add these four secrets:

### Required Secrets:

#### 1. `KEYSTORE_BASE64`
- This is your keystore file encoded in base64
- **Value:** Copy the entire content of `keystore_base64.txt` from your repository
- To get this value locally:
  ```bash
  cat keystore_base64.txt
  ```
- Copy the entire output and paste it here

#### 2. `KEYSTORE_PASSWORD`
- **Value:** `filemanager123`

#### 3. `KEY_ALIAS`
- **Value:** `filemanager`

#### 4. `KEY_PASSWORD`
- **Value:** `filemanager123`

## 🚀 Step 2: Trigger the Workflow

The workflow automatically runs on:
- **Every push to `main` branch** - Builds APK and creates a release
- **Pull requests to `main`** - Builds APK for testing
- **Manual trigger** - Use Actions tab to run manually

### Manual Trigger:
1. Go to **Actions** tab in your repository
2. Select **Build and Sign APK** workflow
3. Click **Run workflow**
4. Choose your branch and click **Run workflow**

## 📦 Step 3: Download Built APKs

After the workflow completes:

### Option 1: Download from Artifacts
1. Go to **Actions** tab
2. Click the latest workflow run
3. Scroll down to **Artifacts**
4. Download `FileManager-APK.zip`

### Option 2: Download from Release
1. Go to **Releases** section
2. Download:
   - `app-release.apk` (Signed release build - recommended)
   - `app-debug.apk` (Debug build)

### Option 3: Direct URL
```
https://github.com/notrealtze/Terminal/releases/download/v1.0-BUILD_NUMBER/app-release.apk
```

## 🔍 Workflow Details

The GitHub Actions workflow does:

1. **Checkout Code** - Gets your latest code
2. **Setup Java 11** - Installs JDK 11
3. **Build Debug APK** - Creates debug build
4. **Build Release APK** - Creates signed release build using keystore
5. **Sign APK** - Decodes keystore from secrets and signs the APK
6. **Upload Artifacts** - Makes APK available for download
7. **Create Release** - Publishes a GitHub Release with the APK

## 📝 Build Output Files

After a successful build:

```
build/
├── outputs/
│   ├── apk/
│   │   ├── debug/
│   │   │   └── app-debug.apk
│   │   └── release/
│   │       └── app-release.apk
```

## ✅ Verification

To verify your APK is properly signed:

```bash
# Install the APK
adb install app-release.apk

# Check APK signature
jarsigner -verify -verbose app-release.apk
```

## 🔐 Security Notes

- ✅ Secrets are encrypted by GitHub
- ✅ Keystore password is never exposed in logs
- ✅ Only repository maintainers can modify secrets
- ✅ Workflow files are public but secrets are private

## 🐛 Troubleshooting

### Workflow fails with "keystore not found"
- Make sure `KEYSTORE_BASE64` secret is set correctly
- The keystore is automatically decoded during the build

### APK signing fails
- Verify all 4 secrets are set correctly
- Check that passwords match the keystore credentials
- Try rebuilding with manual trigger

### Build fails in Actions but works locally
- Check Java version (should be 11)
- Clear Gradle cache: `./gradlew clean`
- Verify Android SDK versions in build.gradle

## 📚 Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Android Build System](https://developer.android.com/build)
- [Gradle Android Plugin](https://developer.android.com/studio/build)

## 🎯 Next Steps

1. ✅ Add the 4 GitHub Secrets
2. ✅ Push code to trigger workflow
3. ✅ Monitor Actions tab for build progress
4. ✅ Download signed APK from Releases
5. ✅ Install on device with: `adb install app-release.apk`

---

**Your APK is now being automatically built and signed!** 🎉
