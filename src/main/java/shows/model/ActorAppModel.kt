package shows.model

import shows.Actor
import shows.TvShow
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.model.annotations.Observable

@Accessors
@Observable
class ActorAppModel(val actor: Actor?) {
	var name: String? = actor?.name
	var character: String? = actor?.character
	var serie: TvShow? = actor?.tvShow

	fun model() = actor
}