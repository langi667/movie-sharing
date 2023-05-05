package de.stefanlang.jc_demo_app

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityViewModelTest {

    @Test
    fun initialState() {
         val vm = MainActivityViewModel()
        assertEquals(vm.count, 0)
    }

    @Test
    fun testOnClicked() {
        val vm = MainActivityViewModel()
        assertEquals(0, vm.count)

        vm.onButtonClicked()
        assertEquals(1, vm.count)
    }
}