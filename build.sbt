platformTarget in Android := "android-25"

name := "macroid-akka-pingpong"

scalaVersion := "2.11.11"

enablePlugins(AndroidApp)

run <<= run in Android

libraryDependencies ++= Seq(
  "org.macroid" %% "macroid" % "2.0",
  "com.android.support" % "support-v4" % "25.1.0",
  "org.macroid" %% "macroid-akka" % "2.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.16"
)

proguardScala in Android := true
dexMulti in Android := true

// Generic ProGuard rules
proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic"
)

// ProGuard rules for Akka
proguardOptions in Android ++= Seq(
  "-keep class akka.actor.Actor$class { *; }",
  "-keep class akka.actor.LightArrayRevolverScheduler { *; }",
  "-keep class akka.actor.LocalActorRefProvider { *; }",
  "-keep class akka.actor.CreatorFunctionConsumer { *; }",
  "-keep class akka.actor.TypedCreatorFunctionConsumer { *; }",
  "-keep class akka.actor.LocalActorRefProvider$Guardian { *; }",
  "-keep class akka.actor.LocalActorRefProvider$SystemGuardian { *; }",
  "-keep class akka.actor.DefaultSupervisorStrategy { *; }",
  "-keep class akka.dispatch.BoundedDequeBasedMessageQueueSemantics { *; }",
  "-keep class akka.dispatch.UnboundedMessageQueueSemantics { *; }",
  "-keep class akka.dispatch.UnboundedDequeBasedMessageQueueSemantics { *; }",
  "-keep class akka.dispatch.DequeBasedMessageQueueSemantics { *; }",
  "-keep class akka.dispatch.MultipleConsumerSemantics { *; }",
  "-keep class akka.dispatch.UnboundedMailbox { *; }",
  "-keep class akka.event.Logging$LogExt { *; }",
  "-keep class macroid.akka.AkkaAndroidLogger { *; }"
)