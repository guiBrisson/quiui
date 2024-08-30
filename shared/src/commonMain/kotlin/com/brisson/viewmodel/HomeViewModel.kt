package com.brisson.viewmodel

import com.brisson.model.SearchQueryResponse
import com.brisson.repository.AddonPersistence
import com.brisson.repository.Stremio
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stremio: Stremio,
    private val addonPersistence: AddonPersistence,
) : ViewModel() {
    private val mutableHomeState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState.Initial)
    val homeState: StateFlow<HomeViewState> = mutableHomeState.asStateFlow()

    init {
        viewmodelScope.launch {
            addonPersistence.saveAddon("v3-cinemeta.strem.io")
        }
    }

    fun search(query: String) {
        logger.i { "Searching query \"$query\"" }
        viewmodelScope.launch {
            stremio.search(query)
                .onEach { result ->
                    mutableHomeState.update {
                        when (it) {
                            is HomeViewState.Content -> {
                                HomeViewState.Content(sections = it.sections + result)
                            }

                            else -> HomeViewState.Content(sections = result)
                        }
                    }
                }
                .catch { t ->
                    mutableHomeState.update {
                        HomeViewState.Error(message = t.message ?: "An unknown error occurred")
                    }
                    logger.w(throwable = t) { "Search caught an error" }
                }
                .collect()
        }
    }

}

sealed class HomeViewState {
    abstract val isLoading: Boolean

    data object Initial : HomeViewState() {
        override val isLoading: Boolean = true
    }

    data class Content(
        val sections: Map<String, SearchQueryResponse>,
        override val isLoading: Boolean = false
    ) : HomeViewState()

    data class Error(
        val message: String,
        override val isLoading: Boolean = false
    ) : HomeViewState()
}
