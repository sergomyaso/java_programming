package ru.sergomyaso.game.view;

import ru.sergomyaso.game.presenter.GamePresenter;
import ru.sergomyaso.game.presenter.Presenter;

public interface View {

    void updateView();
    Presenter getPresenter();
}
