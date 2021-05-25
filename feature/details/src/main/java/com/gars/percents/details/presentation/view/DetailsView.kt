package com.gars.percents.details.presentation.view

import com.gars.percents.common.BaseObservableSource
import com.gars.percents.details.presentation.model.DetailsViewModel
import com.gars.percents.details.presentation.event.UiDetailsEvent
import io.reactivex.functions.Consumer


interface DetailsView : BaseObservableSource<UiDetailsEvent>, Consumer<DetailsViewModel>