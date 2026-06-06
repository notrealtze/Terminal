#!/usr/bin/env python3
import os
import sys
from pathlib import Path

class FileManager:
    def __init__(self):
        self.current_path = os.path.expanduser("~")
    
    def list_files(self):
        """List files in current directory"""
        try:
            items = os.listdir(self.current_path)
            print(f"\n📁 {self.current_path}")
            print("-" * 50)
            for i, item in enumerate(sorted(items), 1):
                full_path = os.path.join(self.current_path, item)
                if os.path.isdir(full_path):
                    print(f"{i:3}. 📂 {item}/")
                else:
                    size = os.path.getsize(full_path)
                    print(f"{i:3}. 📄 {item} ({size} bytes)")
            print("-" * 50)
        except PermissionError:
            print("❌ Permission denied")
    
    def navigate(self, target):
        """Navigate to a directory"""
        if target == "..":
            self.current_path = os.path.dirname(self.current_path)
        elif target.startswith("/"):
            if os.path.isdir(target):
                self.current_path = target
            else:
                print("❌ Directory not found")
        else:
            new_path = os.path.join(self.current_path, target)
            if os.path.isdir(new_path):
                self.current_path = new_path
            else:
                print("❌ Directory not found")
    
    def create_folder(self, name):
        """Create a new folder"""
        try:
            path = os.path.join(self.current_path, name)
            os.makedirs(path, exist_ok=True)
            print(f"✅ Folder '{name}' created")
        except Exception as e:
            print(f"❌ Error: {e}")
    
    def delete_item(self, name):
        """Delete file or folder"""
        try:
            path = os.path.join(self.current_path, name)
            if os.path.isfile(path):
                os.remove(path)
                print(f"✅ File '{name}' deleted")
            elif os.path.isdir(path):
                import shutil
                shutil.rmtree(path)
                print(f"✅ Folder '{name}' deleted")
        except Exception as e:
            print(f"❌ Error: {e}")
    
    def show_help(self):
        """Show help menu"""
        print("""
📋 COMMANDS:
  ls         - List files
  cd <path>  - Change directory
  cd ..      - Go back
  mkdir <name> - Create folder
  rm <name>  - Delete file/folder
  pwd        - Print current path
  help       - Show this menu
  exit       - Quit
        """)
    
    def run(self):
        """Main loop"""
        print("🗂️  Simple File Manager")
        self.show_help()
        
        while True:
            try:
                cmd = input(f"\n$ {self.current_path}> ").strip()
                
                if not cmd:
                    continue
                
                parts = cmd.split(maxsplit=1)
                command = parts[0]
                arg = parts[1] if len(parts) > 1 else None
                
                if command == "ls":
                    self.list_files()
                elif command == "cd":
                    if arg:
                        self.navigate(arg)
                    else:
                        print("❌ Usage: cd <path>")
                elif command == "pwd":
                    print(self.current_path)
                elif command == "mkdir":
                    if arg:
                        self.create_folder(arg)
                    else:
                        print("❌ Usage: mkdir <name>")
                elif command == "rm":
                    if arg:
                        self.delete_item(arg)
                    else:
                        print("❌ Usage: rm <name>")
                elif command == "help":
                    self.show_help()
                elif command == "exit":
                    print("👋 Goodbye!")
                    break
                else:
                    print(f"❌ Unknown command: {command}")
            
            except KeyboardInterrupt:
                print("\n👋 Goodbye!")
                break
            except Exception as e:
                print(f"❌ Error: {e}")

if __name__ == "__main__":
    fm = FileManager()
    fm.run()
