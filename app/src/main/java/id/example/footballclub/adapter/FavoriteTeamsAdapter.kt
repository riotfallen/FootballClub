package id.example.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.example.footballclub.R.id.team_badge
import id.example.footballclub.R.id.team_name
import id.example.footballclub.model.Favorite
import org.jetbrains.anko.*

/**
* Created by dimassaputra on 8/23/18.
*/

class FavoriteTeamsAdapter(private val favorite : List<Favorite>, private val listener : (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorite.size


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL


                imageView {
                    id = team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }


                textView{
                    id = team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }

}

class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val teamBadge : ImageView = view.find(team_badge)
    private val teamName : TextView = view.find(team_name)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName
        itemView.setOnClickListener { listener(favorite) }
    }

}
