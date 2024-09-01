package com.brisson.viewmodel

import com.brisson.model.Meta
import com.brisson.repository.AddonRepository
import com.brisson.repository.StremioRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stremioRepository: StremioRepository,
    private val addonRepository: AddonRepository,
) : ViewModel() {
    private val mutableHomeState: MutableStateFlow<HomeViewState> =
        MutableStateFlow(HomeViewState.Initial)
    val homeState: StateFlow<HomeViewState> = mutableHomeState.asStateFlow()

    init {
        viewmodelScope.launch {
            addonRepository.installAddon(
                "v3-cinemeta.strem.io",
                "150203dd784e-cinetorrent-addon.baby-beamup.club"
            )
            getHomeCatalog()
        }
    }

    fun getHomeCatalog() {
        mutableHomeState.update { HomeViewState.Loading }
        logger.i { "Loading home catalog" }
        viewmodelScope.launch {
            stremioRepository.homePageCatalog()
                .onEach { result ->
                    mutableHomeState.update {
                        when (it) {
                            is HomeViewState.Content -> HomeViewState.Content(sections = it.sections + result)
                            else -> HomeViewState.Content(sections = result)
                        }
                    }
                }
                .catch { t ->
                    mutableHomeState.update {
                        HomeViewState.Error(message = t.message ?: "An unknown error occurred")
                    }
                    logger.w(throwable = t) { "Loading home catalog caught an error" }
                }
                .collect()
        }
    }

    fun search(query: String) {
        if (query.isEmpty()) {
            getHomeCatalog()
            return
        }

        mutableHomeState.update { HomeViewState.Loading }
        logger.i { "Searching query \"$query\"" }
        viewmodelScope.launch {
            stremioRepository.search(query)
                .onEach { result ->
                    result.mapValues { it.value.metas }.let { sections ->
                        mutableHomeState.update {
                            when (it) {
                                is HomeViewState.Content -> {
                                    HomeViewState.Content(sections = it.sections + sections)
                                }
                                else -> HomeViewState.Content(sections = sections)
                            }
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
    data object Initial : HomeViewState()
    data object Loading : HomeViewState()
    data class Content(val sections: Map<String, List<Meta>>) : HomeViewState()
    data class Error(val message: String) : HomeViewState()
}
