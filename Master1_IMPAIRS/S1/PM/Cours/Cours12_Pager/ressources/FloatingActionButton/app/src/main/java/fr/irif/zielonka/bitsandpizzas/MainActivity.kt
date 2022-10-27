package fr.irif.zielonka.bitsandpizzas

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import fr.irif.zielonka.bitsandpizzas.data.PastaItem
import fr.irif.zielonka.bitsandpizzas.data.PizzaItem

import fr.irif.zielonka.bitsandpizzas.data.StoreItem
import fr.irif.zielonka.bitsandpizzas.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    val binding : ActivityMainBinding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val names = listOf("PIZZA", "PASTA", "STORE")
        val pizzaNames = resources.getStringArray(R.array.pizzas).toMutableList()
        val pastaNames = resources.getStringArray(R.array.pasta).toMutableList()
        val storeNames = resources.getStringArray(R.array.stores).toMutableList()
        val pizzas = MutableList<PizzaItem>(pizzaNames.size) { PizzaItem(pizzaNames[it]) }
        val pastas = MutableList<PastaItem>(pastaNames.size) { PastaItem(pastaNames[it]) }
        val stores = MutableList<StoreItem>(storeNames.size) { StoreItem(storeNames[it]) }
        val pizzaAdapter = PizzaAdapter(pizzas)
        val pastaAdapter = PastaAdapter(pastas)
        val storeAdapter = StoreAdapter(stores)
        val pizzaFragment =
            PizzaItemFragment.newInstance(names[0], pizzaAdapter, R.layout.fragment_layout)
        val pastaFragment =
            PastaItemFragment.newInstance(names[1], pastaAdapter, R.layout.fragment_layout)
        val storeFragment =
            StoreItemFragment.newInstance(names[2], storeAdapter, R.layout.fragment_layout)
        val pagerAdapter = ScreenSlidePagerAdapter(
            this,
            mutableListOf<Fragment>(pizzaFragment, pastaFragment, storeFragment)
        )
       binding.pager.adapter = pagerAdapter

        /* donner les noms au tabs et les attacher aux fragments correspondants*/

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = names[position]
        }.attach()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action"){ Toast.makeText(this, "snack bar marche", Toast.LENGTH_SHORT).show()  }
                .show()
        }
       // binding.pager.setPageTransformer( ZoomOutPageTransformer())
       // binding.pager.setPageTransformer( DepthPageTransformer())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    /* sans onBackPressed() le bouton back termine (finish()) l'activité */
    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
            //binding.pager.currentItem = binding.pager.adapter!!.getItemCount() - 1
        } else {
            // Otherwise, select the previous step.
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
    }
}

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}


class DepthPageTransformer : ViewPager2.PageTransformer {
    companion object{
        private const val MIN_SCALE = 0.75f
    }
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> { // (0,1]
                    // Fade the page out.
                    alpha = 1 - position

                    // Counteract the default slide transition
                    translationX = pageWidth * -position

                    // Scale the page down (between MIN_SCALE and 1)
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}