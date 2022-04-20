package com.example.findmysquad.Repository.ConfigRepository

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.ChipGroup
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ConfigRepository : IConfigRepository {

    private var timerDate: String = ""
    private var nickname: String = ""

    override suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context,
        img: ImageView
    ) {
        if (getNickname(et).isNotEmpty()) {
            val profileMap = hashMapOf(
                "nickname" to getNickname(et),
                "horario" to timerDate,
                "lista-jogos" to filterChip(chipGroup),
                "lista-plataformas" to filterChip(chipGroup2),
                "img" to uploadImg(img)
            )

            FirebaseFeatures.getDatabase().collection("User")
                .document(FirebaseFeatures.getAuth().currentUser?.uid.toString()).set(profileMap)
                .addOnSuccessListener {
                    context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Erro ao continuar cadastro: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

           uploadImg(img)
        }
    }

    override fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }

    private fun getNickname(et: EditText): String {
        nickname = et.text.toString()
        return nickname
    }

    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

    private fun uploadImg(img: ImageView) : String{
        val bitmap = (img.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        val data = baos.toByteArray()

        FirebaseFeatures.getStorage().reference.child("imagens").child("img1").putBytes(data)
        return data.toString()
    }

}