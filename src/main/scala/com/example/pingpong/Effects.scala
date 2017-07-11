/**
  * https://github.com/AdrianRaFo
  */
package com.example.pingpong

import macroid.FullDsl._


/** Effects for out widgets */
object Effects {
  // make a glorious fade
  def appear =
    fadeIn(600) +
      enable

  // disappear with style
  def disappear =
    disable ++
      fadeOut(600) ++
      delay(600)
}
