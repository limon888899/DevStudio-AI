package com.devstudio.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devstudio.ai.data.models.CodeFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class EditorState {
    data object Loading : EditorState()
    data class Loaded(val files: List<CodeFile>, val currentFile: CodeFile) : EditorState()
    data class Error(val message: String) : EditorState()
}

class EditorViewModel : ViewModel() {

    private val _editorState = MutableStateFlow<EditorState>(EditorState.Loading)
    val editorState: StateFlow<EditorState> = _editorState.asStateFlow()

    private val _files = MutableStateFlow<List<CodeFile>>(emptyList())
    val files: StateFlow<List<CodeFile>> = _files.asStateFlow()

    private val _currentFile = MutableStateFlow<CodeFile?>(null)
    val currentFile: StateFlow<CodeFile?> = _currentFile.asStateFlow()

    private val mockFiles = listOf(
        CodeFile("App.tsx", "App.tsx", "const App = () => { return <div>Hello DevStudio</div> }", "typescript"),
        CodeFile("index.html", "index.html", "<!DOCTYPE html>\n<html>\n<body>\n<div id='root'></div>\n</body>\n</html>", "html"),
        CodeFile("styles.css", "styles.css", "body { margin: 0; padding: 0; background: #0D1117; }", "css"),
        CodeFile("app.js", "app.js", "console.log('DevStudio AI');", "javascript"),
        CodeFile("config.json", "config.json", "{\n  \"name\": \"DevStudio AI\",\n  \"version\": \"1.0.0\"\n}", "json")
    )

    init {
        loadFiles()
    }

    private fun loadFiles() {
        viewModelScope.launch {
            try {
                _files.value = mockFiles
                _currentFile.value = mockFiles[0]
                _editorState.value = EditorState.Loaded(mockFiles, mockFiles[0])
            } catch (e: Exception) {
                _editorState.value = EditorState.Error(e.message ?: "Error loading files")
            }
        }
    }

    fun selectFile(file: CodeFile) {
        viewModelScope.launch {
            _currentFile.value = file
        }
    }

    fun updateFileContent(content: String) {
        viewModelScope.launch {
            _currentFile.value = _currentFile.value?.copy(content = content)
        }
    }
}

class EditorViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditorViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
