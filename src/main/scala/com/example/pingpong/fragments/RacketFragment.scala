package com.example.pingpong.fragments

import android.view.{ ViewGroup, LayoutInflater}
import android.os.Bundle
import android.widget.{FrameLayout, Button}

import macroid._
import macroid.FullDsl._
import macroid.akka.AkkaFragment

import scala.concurrent.ExecutionContext.Implicits.global

import com.example.pingpong.Effects
import com.example.pingpong.Styles
import com.example.pingpong.actors.RacketActor

/** Our UI fragment */
class RacketFragment extends AkkaFragment with Contexts[AkkaFragment] {
  // get actor name from arguments
  lazy val actorName = getArguments.getString("name")

  // actor for this fragment
  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  // a slot for the racket button
  var racket = slot[Button]

  // trigger the fadeIn effect
  def receive = racket <~ Effects.appear

  // smash the ball
  def smash =
    // wait until the racket disappears
    (racket <~~ Effects.disappear) ~~
      // tell the actor to smash
      Ui(actor.foreach(_ ! RacketActor.Smash))

  override def onCreateView(inflater: LayoutInflater,
                            container: ViewGroup,
                            savedInstanceState: Bundle) = Ui.get {
    l[FrameLayout](
      w[Button] <~ wire(racket) <~ Styles.racket(actorName) <~ On.click(smash)
    )
  }
}
