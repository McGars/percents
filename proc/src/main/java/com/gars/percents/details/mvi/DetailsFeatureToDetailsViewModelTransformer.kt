package com.gars.percents.details.mvi

import com.gars.percents.details.mvi.DetailsFeature.State.Status
import com.gars.percents.details.presentation.DetailsViewModel


class DetailsFeatureToDetailsViewModelTransformer: (DetailsFeature.State) -> DetailsViewModel {

    override fun invoke(state: DetailsFeature.State): DetailsViewModel = DetailsViewModel(
        state = state.status.toDetailsState(),
        month = state.month,
        actuals = state.accrual
    )

    private fun Status.toDetailsState(): DetailsViewModel.State = when(this) {
        Status.IDLE -> DetailsViewModel.State.IDLE
        Status.LOADING -> DetailsViewModel.State.LOADING
        Status.DATA -> DetailsViewModel.State.DATA
        Status.ERROR -> DetailsViewModel.State.ERROR
    }

}