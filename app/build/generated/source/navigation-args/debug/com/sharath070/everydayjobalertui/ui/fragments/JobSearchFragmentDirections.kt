package com.sharath070.everydayjobalertui.ui.fragments

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.sharath070.everydayjobalertui.R

public class JobSearchFragmentDirections private constructor() {
  public companion object {
    public fun actionJobSearchFragmentToWebViewFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_jobSearchFragment_to_webViewFragment)
  }
}
