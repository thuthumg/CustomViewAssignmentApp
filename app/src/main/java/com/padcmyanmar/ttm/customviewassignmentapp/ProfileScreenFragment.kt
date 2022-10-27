package com.padcmyanmar.ttm.customviewassignmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout

class ProfileScreenFragment  : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: ViewGroup
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var viewPager: ViewPager
    private lateinit var appBarLayout: AppBarLayout

    override fun onStart() {
        super.onStart()
        bottomSheet =
            dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup // notice the R root package
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        // SETUP YOUR BEHAVIOR HERE
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(
                        appBarLayout,
                        getActionBarSize()
                    ) // show app bar when expanded completely
                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(appBarLayout) // hide app bar when collapsed
                }
                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss() // destroy the instance
                }
            }

            override fun onSlide(view: View, v: Float) {}
        })

   //     hideAppBar(appBarLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun hideAppBar(view: View) {
        val params = view.layoutParams
        params.height = 0
        view.layoutParams = params
    }

    private fun showView(view: View, size: Int) {
        val params = view.layoutParams
        params.height = size
        view.layoutParams = params
    }

    private fun getActionBarSize(): Int {
        val styledAttributes =
            requireContext().theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        return styledAttributes.getDimension(0, 0f).toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myview: View = inflater.inflate(R.layout.bottom_sheet, container, false)

        // SETUP THE VIEWPAGER AND THE TABLAYOUT HERE

        val tabLayout = myview.findViewById<TabLayout>(R.id.tabLayout)

        setUpTabLayout(tabLayout = tabLayout)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // USE childFragmentManager
        //  val adapter = MyFragmentAdapter(childFragmentManager)
        // viewPager.adapter = adapter
        //  viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
              //  viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return myview
    }

    private fun setUpTabLayout(tabLayout: TabLayout) {

        val taskListArray: Array<String> = arrayOf("Project Tasks", "Contacts", "About you")

        taskListArray.forEach {
            tabLayout.newTab().apply {
                text = it
                tabLayout.addTab(this)
            }

        }

    }
}

/* : BottomSheetDialogFragment() {

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
 }

 override fun onCreateView(
     inflater: LayoutInflater,
     container: ViewGroup?,
     savedInstanceState: Bundle?
 ): View? {

  var  view =  inflater.inflate(R.layout.bottom_sheet,container,false)
   val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
     setUpTabLayout(tabLayout)
     return view
 }
 private fun setUpTabLayout(tabLayout: TabLayout) {

     val taskListArray: Array<String> = arrayOf("Project Tasks","Contacts","About you")

     taskListArray.forEach {
        tabLayout.newTab().apply {
             text = it
             tabLayout.addTab(this)
         }

     }
 }*/
//    public BottomSheetFragment() {
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.bottom_sheet, container, false);
//    }
/*}*/