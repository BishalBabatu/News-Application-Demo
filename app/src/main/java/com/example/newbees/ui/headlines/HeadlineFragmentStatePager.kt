package com.example.newbees.ui.headlines

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newbees.ui.headlines.categories.business.BusinessNewsFragment
import com.example.newbees.ui.headlines.categories.entertainment.EntertainmentNewsFragment
import com.example.newbees.ui.headlines.categories.health.HealthNewsFragment
import com.example.newbees.ui.headlines.categories.science.ScienceNewsFragment
import com.example.newbees.ui.headlines.categories.sports.SportsNewsFragment
import com.example.newbees.ui.headlines.categories.technology.TechNewsFragment
import com.example.newbees.utils.AppConstants.FIRST_TAB
import com.example.newbees.utils.AppConstants.FOURTH_TABS
import com.example.newbees.utils.AppConstants.SECOND_TABS
import com.example.newbees.utils.AppConstants.START_TAB
import com.example.newbees.utils.AppConstants.THIRD_TABS
import com.example.newbees.utils.AppConstants.TOTAL_TABS

class HeadlineFragmentStatePager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return TOTAL_TABS
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            START_TAB -> BusinessNewsFragment()
            FIRST_TAB -> EntertainmentNewsFragment()
            SECOND_TABS -> HealthNewsFragment()
            THIRD_TABS -> ScienceNewsFragment()
            FOURTH_TABS -> SportsNewsFragment()
            else -> TechNewsFragment()
        }
    }


}