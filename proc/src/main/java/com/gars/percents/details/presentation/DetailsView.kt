package com.gars.percents.details.presentation

import com.gars.percents.base.BaseObservableSource
import io.reactivex.functions.Consumer


interface DetailsView : BaseObservableSource<UiDetailsEvent>, Consumer<DetailsViewModel>