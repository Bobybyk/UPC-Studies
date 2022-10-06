package fr.irif.zielonka.fichiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import fr.irif.zielonka.fichiers.databinding.ActivityMainBinding
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    //j'utilise ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun action(view: View) {
        val fileName = binding.fileName.text.toString()
        if (fileName.isEmpty()) {
            Toast.makeText(this, "empty file name", Toast.LENGTH_SHORT).show()
            return
        }

        val contentText = binding.content.text

        var file: File
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.external -> {
                file = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)
            }
            R.id.internal -> {
                file = File(filesDir, fileName)
            }
            else -> throw IllegalArgumentException("file type")
        }
        when (binding.readwriteGroup.checkedRadioButtonId) {
            R.id.write -> {
                file.writer().use {  it.append( contentText ) }
                contentText.clear()
            }
            R.id.read -> {
               /* file.reader().use {
                    val lines = it.readLines()
                    contentText.clear()
                    for (s in lines)
                        contentText.append(s).append('\n')
                }*/

                val toto = file.reader().useLines{
                    lines -> lines.reduce{some, tex -> "$some\n$tex"}
                }
                contentText.clear()
                contentText.append(toto)

            }
            R.id.append -> {
                /* FileWriter( file, true) -> true pour avoir le fichier ouvert en mode append */
                FileWriter(file, true).use{
                    it.append( contentText )
                }
                contentText.clear()
            }
        }
    }
}