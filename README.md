# Android File Manager

A lightweight Android file manager application with a simple and intuitive interface.

## 📥 Downloads

Download the APK directly from the releases or clone this repository:

```bash
git clone https://github.com/notrealtze/Terminal.git
```

## 🚀 Features

- Browse files and folders on your device
- Navigate with Back and Home buttons
- Visual file/folder indicators (📁 folders, 📄 files)
- Sorted file display
- Click folders to explore contents

## 📦 Files Included

- **FileManager.apk** - Ready-to-install Android application
- **MainActivity.java** - Main app logic and file browsing
- **activity_main.xml** - UI layout
- **AndroidManifest.xml** - App configuration
- **strings.xml** - App resources
- **build_apk.sh** - Build script

## 📱 Installation

### Option 1: Direct APK Install
```bash
adb install FileManager.apk
```

### Option 2: Build from Source
For a full production build with proper compilation:
1. Download the source files
2. Import into Android Studio
3. Build using Gradle: `./gradlew build`

## 🔧 Requirements

- Android 5.0+ (API Level 21+)
- File storage permissions

## 📝 Permissions

- `READ_EXTERNAL_STORAGE` - Browse files
- `WRITE_EXTERNAL_STORAGE` - Create/modify files
- `MANAGE_EXTERNAL_STORAGE` - Full storage access

## 📄 License

Open source - Feel free to modify and distribute

## 🛠️ Build Without Gradle

To build quickly without Gradle:
```bash
./build_apk.sh
```

---

**Download now and manage your files on the go!** 📂
