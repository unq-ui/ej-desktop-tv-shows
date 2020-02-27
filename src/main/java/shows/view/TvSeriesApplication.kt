package shows.view

import shows.model.TvShowsAppModel
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

fun main() = TvSeriesApplication().start()

class TvSeriesApplication : Application() {
	override fun createMainWindow(): Window<*> = TvSeriesWindow(this, TvShowsAppModel())
}
