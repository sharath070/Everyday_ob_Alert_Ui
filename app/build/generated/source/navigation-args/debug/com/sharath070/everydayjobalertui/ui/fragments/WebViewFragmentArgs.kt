package com.sharath070.everydayjobalertui.ui.fragments

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class WebViewFragmentArgs(
  public val link: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("link", this.link)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("link", this.link)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): WebViewFragmentArgs {
      bundle.setClassLoader(WebViewFragmentArgs::class.java.classLoader)
      val __link : String?
      if (bundle.containsKey("link")) {
        __link = bundle.getString("link")
        if (__link == null) {
          throw IllegalArgumentException("Argument \"link\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"link\" is missing and does not have an android:defaultValue")
      }
      return WebViewFragmentArgs(__link)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): WebViewFragmentArgs {
      val __link : String?
      if (savedStateHandle.contains("link")) {
        __link = savedStateHandle["link"]
        if (__link == null) {
          throw IllegalArgumentException("Argument \"link\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"link\" is missing and does not have an android:defaultValue")
      }
      return WebViewFragmentArgs(__link)
    }
  }
}
