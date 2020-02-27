package shows.view

import shows.model.ActorAppModel
import shows.model.TvShowAppModel
import shows.model.TvShowsAppModel
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.List
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.kotlin.extensions.*

class TvSeriesWindow(owner: WindowOwner, model: TvShowsAppModel)
	: SimpleWindow<TvShowsAppModel>(owner, model) {
	override fun addActions(actionsPanel: Panel) {}
	override fun createFormPanel(mainPanel: Panel) {}

	override fun createMainTemplate(mainPanel: Panel) {
		title = "TV Series"
		iconImage = shows.ICON

		mainPanel.asHorizontal()
		makeSeriesListPanel(mainPanel)
		makeSelectedSerieEditionPanel(mainPanel)
	}
	
	private fun makeSeriesListPanel(owner: Panel) {
		Panel(owner) with {
			asVertical()
			Label(it) with { text = "Series" }
			List<TvShowAppModel>(it) with {
				bindTo("selectedTvShow")
				bindItemsTo("tvShows").adaptWithProp<TvShowAppModel>("name")
			}
			Button(it) with {
				caption = "Nueva Serie"
				onClick {
					AddSerieDialog(thisWindow, TvShowAppModel(null), thisWindow.modelObject).open()
				}
			}
		}
	}

	private fun makeSelectedSerieEditionPanel(owner: Panel) {
		Panel(owner) with { it ->
			Panel(it) with {
				asColumns(2)
				Label(it) with { text = "Nombre:" }
				TextBox(it) with {
					width = 200
					bindTo("selectedTvShow.name")
				}

				Label(it) with { text  = "Temporadas:" }
				TextBox(it) with {
					width = 200
					bindTo("selectedTvShow.seasons")
				}
			}

			table<ActorAppModel>(it) {
				bindItemsTo("selectedTvShow.actors")
				bindTo("selectedActor")
				column {
					title = "Nombre"
					bindContentsTo("name")
				}
				column {
					title = "Personaje"
					bindContentsTo("character")
				}
			}

			Button(it) with {
				caption = "Borrar actor"
				onClick {
					RemoveActorDialog(thisWindow, thisWindow.modelObject.selectedActor) with {
						onAccept { removeSelectedActor() }
						open()
					}
				}
			}
		}
	}

	private fun removeSelectedActor() = modelObject.removeSelectedActor()
}
