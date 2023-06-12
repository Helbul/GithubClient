package com.gb.poplib.githubclient

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(counter: Int) {
        when(counter) {
            COUNTER_1 -> {
                val nextValue = model.next(COUNTER_1)
                view.setButtonText(COUNTER_1, nextValue.toString())
            }
            COUNTER_2 -> {
                val nextValue = model.next(COUNTER_2)
                view.setButtonText(COUNTER_2, nextValue.toString())
            }
            COUNTER_3 -> {
                val nextValue = model.next(COUNTER_3)
                view.setButtonText(COUNTER_3, nextValue.toString())
            }
        }
    }

    companion object {
        const val COUNTER_1 = 0
        const val COUNTER_2 = 1
        const val COUNTER_3 = 2
    }
}