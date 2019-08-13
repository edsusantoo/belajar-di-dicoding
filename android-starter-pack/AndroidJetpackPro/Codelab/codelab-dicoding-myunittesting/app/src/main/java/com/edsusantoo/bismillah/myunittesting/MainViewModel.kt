package com.edsusantoo.bismillah.myunittesting

class MainViewModel(private var cuboidModel: CuboidModel) {

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(w, l, h)
    }

    fun getCircumference(): Double {
        return cuboidModel.getCircumference()
    }

    fun getSurfaceArea(): Double {
        return cuboidModel.getSurfaceArea()
    }

    fun getVolume(): Double {
        return cuboidModel.getVolume()
    }

}