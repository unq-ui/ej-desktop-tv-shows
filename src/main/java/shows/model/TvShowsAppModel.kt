package shows.model

import shows.TvShowsSystem
import shows.TvShowsFactory
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Accessors
@Observable
class TvShowsAppModel {
	private val system: TvShowsSystem = TvShowsFactory.makeTvShowsSystem()
	var tvShows = initSeries()
	var selectedTvShow: TvShowAppModel? = null
	var selectedActor: ActorAppModel? = null

	fun removeSelectedActor() {
		if (selectedActor == null) throw UserException("No se seleccionó Actor o Serie")

		system.removeActorFromTvShow(selectedActor!!.model(), selectedActor!!.serie)
		tvShows = initSeries()
	}

	private fun initSeries() = system.tvShows.map { TvShowAppModel(it) }.toMutableList()

	fun addNewSerie(tvShow: TvShowAppModel): Unit {
		if (tvShow.name == null || tvShow.seasons == null)
			throw UserException("Falta el nombre o las temporadas")

		system.addTvShow(tvShow.model())
		tvShows = initSeries()
	}
}
