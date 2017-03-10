package io.rollhax.wheniworkchallenge.presentation.presenter;

import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.wheniworkchallenge.presentation.IRefreshable;
import io.rollhax.wheniworkchallenge.view.activity.IDeparturesListView;

public interface IDeparturesPresenter extends IRefreshable {
    void onCreate(IDeparturesListView presentation, String route, DirectionType directionType,
                  String stopId);
}
