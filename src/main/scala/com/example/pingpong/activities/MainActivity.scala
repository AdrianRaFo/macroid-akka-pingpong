/**
  * https://github.com/AdrianRaFo
  */
package com.example.pingpong.activities

import android.os.Bundle
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams._
import android.support.v4.app.FragmentActivity

import macroid._
import macroid.FullDsl._
import macroid.akka.AkkaActivity

import com.example.pingpong.actors.RacketActor
import com.example.pingpong.fragments.RacketFragment

/** The main activity */
class MainActivity
    extends FragmentActivity
    with Contexts[FragmentActivity] with AkkaActivity  {
  val actorSystemName = "pingpong"

  object Id extends IdGenerator(start = 1000)

  // players
  lazy val ping = actorSystem.actorOf(RacketActor.props, "ping")
  lazy val pong = actorSystem.actorOf(RacketActor.props, "pong")


  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    // initialize the actors
    (ping, pong)

    // layout params
    val lps = lp[LinearLayout](MATCH_PARENT, WRAP_CONTENT, 1.0f)

    // include the two fragments
    val view = l[LinearLayout](
      // we pass a name for the actor, and id+tag for the fragment
      f[RacketFragment].pass(Bundles.bundle("name" -> "ping")).framed(Id.ping, Tag.ping) <~ lps,
      f[RacketFragment].pass(Bundles.bundle("name" -> "pong")).framed(Id.pong, Tag.pong) <~ lps
    ) <~ vertical

    setContentView(Ui.get(view))
  }


  override def onStart() = {
    super.onStart()

    // start the game
    ping.tell(RacketActor.Ball, pong)
  }
}
