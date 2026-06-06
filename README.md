# Android File Manager

A lightweight Android file manager application with a simple and intuitive interface.

## ✅ Status

**Working APK ready to download and install!**

## 📥 Downloads

### Direct Download
- **[FileManager.apk](https://github.com/notrealtze/Terminal/raw/main/FileManager.apk)** - Ready to install on Android devices

### Clone Repository
```bash
git clone https://github.com/notrealtze/Terminal.git
```

## 🚀 Features

- ✅ Browse files and folders on your device
- ✅ Navigate with Back and Home buttons
- ✅ Visual file/folder indicators (📁 folders, 📄 files)
- ✅ Sorted file display
- ✅ Click folders to explore contents
- ✅ Works on Android 5.0+ (API Level 21+)

## 📦 Project Structure

```
.
├── FileManager.apk              # Ready-to-install APK
├── src/main/
│   ├── java/com/filemanager/app/
│   │   └── MainActivity.java      # Main app logic
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml  # UI layout
│   │   └── values/
│   │       ├── strings.xml        # App strings
│   │       ├── colors.xml         # Color palette
│   │       └── styles.xml         # Styles
│   └── AndroidManifest.xml        # App configuration
├── build_apk_manual.py            # Python build script (alternative to Gradle)
└── README.md                       # This file
```

## 📱 Installation

### Option 1: Install APK via ADB
```bash
adb install FileManager.apk
```

### Option 2: Install APK directly on device
1. Download `FileManager.apk`
2. Transfer to your Android device
3. Open file manager and tap the APK to install
4. Follow prompts to complete installation

### Option 3: Build from Source

#### Using Python (No Gradle/SDK needed)
```bash
python3 build_apk_manual.py
```
This creates a new APK from the source files.

#### Using Android Studio
1. Clone the repository
2. Open in Android Studio
3. Build → Build Bundle(s) / APK(s)

## 🔧 Requirements

- **For Installation:**
  - Android 5.0+ (API Level 21+)
  - ~2 MB disk space

- **For Building:**
  - Python 3.6+ (for manual build)
  - Java 11+ (for compilation)
  - Optional: Android Studio or Gradle for full builds

## 📝 Permissions

The app requests the following permissions to function:
- `READ_EXTERNAL_STORAGE` - Browse files
- `WRITE_EXTERNAL_STORAGE` - Create/modify files  
- `MANAGE_EXTERNAL_STORAGE` - Full storage access (Android 11+)

## 🛠️ Build Methods

### Method 1: Python Build Script (Easiest - No Gradle)
```bash
python3 build_apk_manual.py
```

### Method 2: Android Studio (Most Reliable)
1. Import project into Android Studio
2. Sync Gradle files
3. Build → Build APK(s)

### Method 3: Gradle Command Line
```bash
./gradlew build
```

## 📄 License

Open source - Feel free to modify and distribute

## 🤝 Contributing

Feel free to fork, modify, and submit improvements!

## 📸 Usage

1. **Launch the app** - Tap the "File Manager" icon
2. **Browse files** - You'll start in your storage root
3. **Navigate folders** - Tap any folder to open it
4. **Go back** - Use the "← Back" button
5. **Go home** - Use the "🏠 Home" button to return to storage root
6. **View file details** - Tap a file to see its name

---

**Download and enjoy managing your files!** 📂
