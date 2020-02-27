package shows.model

import shows.TvShow
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
@Accessors
data class TvShowAppModel(var tvShow: TvShow?) {
	var id: Int? = tvShow?.id
	var name: String? = tvShow?.name
	var seasons: Int? = tvShow?.seasons
	var actors: MutableList<ActorAppModel>? = tvShow?.actors?.map { ActorAppModel(it) }?.toMutableList()

	fun model() = tvShow
	fun createSerie() {
		if (name == null || seasons == null)
			throw UserException("Debe completar los datos")

		id = 42
		tvShow = TvShow(id!!, name!!, seasons!!)
	}
}