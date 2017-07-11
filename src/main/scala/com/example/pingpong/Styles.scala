/**
  * https://github.com/AdrianRaFo
  */
package com.example.pingpong

import android.os._
import android.view.ViewGroup.LayoutParams._
import android.view._
import android.widget._
import macroid.FullDsl._
import macroid.akka._
import macroid.contrib.TextTweaks
import macroid.{Ui, _}
import scala.concurrent.ExecutionContext.Implicits.global

/** Styles for our widgets */
object Styles {
  // how racket looks
  def racket(str: String)(implicit appCtx: ContextWrapper) =
    hide + disable +
      text(str) +
      TextTweaks.large +
      lp[FrameLayout](WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
}
