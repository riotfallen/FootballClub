package id.example.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.example.footballclub.model.Team
import org.jetbrains.anko.*

import id.example.footballclub.R

/**
* Created by dimassaputra on 8/13/18.
*/
class TeamsAdapter (private val teams : List<Team>, private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                }
            }
        }

    }

}

class TeamViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val teamBadge : ImageView = view.findViewById(R.id.team_badge)
    private val teamName : TextView = view.findViewById(R.id.team_name)

    fun bindItem(teams: Team, listener: (Team) -> Unit){

        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName

        itemView.setOnClickListener { listener(teams) }

    }

}
