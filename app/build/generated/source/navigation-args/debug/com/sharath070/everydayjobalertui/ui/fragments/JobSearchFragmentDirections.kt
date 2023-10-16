package com.sharath070.everydayjobalertui.ui.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.sharath070.everydayjobalertui.R
import kotlin.Int
import kotlin.String

public class JobSearchFragmentDirections private constructor() {
  private data class ActionJobSearchFragmentToWebViewFragment(
    public val link: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_jobSearchFragment_to_webViewFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("link", this.link)
        return result
      }
  }

  public companion object {
    public fun actionJobSearchFragmentToWebViewFragment(link: String): NavDirections =
        ActionJobSearchFragmentToWebViewFragment(link)
  }
}
