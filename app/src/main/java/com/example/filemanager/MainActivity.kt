package com.example.filemanager

import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile

class MainActivity : AppCompatActivity() {
    private var currentUri: Uri? = null
    private var navigationStack = mutableListOf<Uri>()
    private lateinit var fileListView: ListView
    private lateinit var pathTextView: TextView
    private lateinit var backButton: Button
    private lateinit var selectButton: Button
    private var fileItems = mutableListOf<FileItem>()
    private lateinit var adapter: ArrayAdapter<String>

    private val selectFolderLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { uri: Uri? ->
        if (uri != null) {
            currentUri = uri
            navigationStack.clear()
            navigationStack.add(uri)
            loadFiles(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fileListView = findViewById(R.id.fileListView)
        pathTextView = findViewById(R.id.pathTextView)
        backButton = findViewById(R.id.backButton)
        selectButton = findViewById(R.id.selectButton)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        fileListView.adapter = adapter

        selectButton.setOnClickListener {
            selectFolderLauncher.launch(null)
        }

        backButton.setOnClickListener {
            if (navigationStack.size > 1) {
                navigationStack.removeAt(navigationStack.size - 1)
                loadFiles(navigationStack.last())
            }
        }

        fileListView.setOnItemClickListener { _, _, position, _ ->
            if (position < fileItems.size) {
                val item = fileItems[position]
                if (item.isDirectory && item.uri != null) {
                    navigationStack.add(item.uri)
                    loadFiles(item.uri)
                }
            }
        }

        pathTextView.text = "No folder selected"
        backButton.isEnabled = false
    }

    private fun loadFiles(folderUri: Uri) {
        val documentFile = DocumentFile.fromTreeUri(this, folderUri) ?: return
        fileItems.clear()

        try {
            documentFile.listFiles().forEach { file ->
                fileItems.add(
                    FileItem(
                        name = file.name ?: "Unknown",
                        size = if (file.isFile) file.length() else 0,
                        isDirectory = file.isDirectory,
                        uri = file.uri,
                        lastModified = file.lastModified()
                    )
                )
            }
        } catch (e: Exception) {
            // Handle permission errors
        }

        // Sort: directories first, then by name
        fileItems.sortWith(compareBy({ !it.isDirectory }, { it.name }))

        // Update UI
        val displayList = fileItems.map { item ->
            val type = if (item.isDirectory) "[DIR]" else "[FILE]"
            val sizeStr = if (item.isDirectory) "" else " (${formatSize(item.size)})"
            "${item.name} $type$sizeStr"
        }

        adapter.clear()
        adapter.addAll(displayList)
        adapter.notifyDataSetChanged()

        pathTextView.text = documentFile.name ?: "File Manager"
        backButton.isEnabled = navigationStack.size > 1
    }

    private fun formatSize(bytes: Long): String {
        return when {
            bytes >= 1024 * 1024 * 1024 -> String.format("%.2f GB", bytes / (1024f * 1024f * 1024f))
            bytes >= 1024 * 1024 -> String.format("%.2f MB", bytes / (1024f * 1024f))
            bytes >= 1024 -> String.format("%.2f KB", bytes / 1024f)
            else -> "$bytes B"
        }
    }

    data class FileItem(
        val name: String,
        val size: Long,
        val isDirectory: Boolean,
        val uri: Uri?,
        val lastModified: Long
    )
}
