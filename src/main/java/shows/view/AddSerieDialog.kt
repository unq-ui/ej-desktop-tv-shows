package shows.view

import shows.model.TvShowAppModel
import shows.model.TvShowsAppModel
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*

class AddSerieDialog(owner: WindowOwner, model: TvShowAppModel, private val system: TvShowsAppModel)
	: Dialog<TvShowAppModel>(owner, model) {
	override fun createFormPanel(mainPanel: Panel) {
		title = "Nueva Serie"
		mainPanel.asVertical()
		
		Label(mainPanel) with { text = "Nombre:" }
		TextBox(mainPanel) with {
			width = 200
			bindTo("name")
		}

		Label(mainPanel) with { text = "Temporadas:" }
		TextBox(mainPanel) with {
			width = 200
			bindTo("seasons")
		}

		Button(mainPanel) with {
			caption = "Aceptar"
			onClick {
				modelObject.createSerie()
				system.addNewSerie(modelObject)
				accept()
			}
		}
	}
	
}