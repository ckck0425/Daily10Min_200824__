package kr.co.tjoeun.daily10min_200824

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import okhttp3.internal.http2.Http2Reader
import java.util.logging.Handler

class MyFCMService  : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        Log.d("새로발급된기기토큰", p0)

    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d("받은메세지", p0.notification?.title.toString())
        Log.d("받은메세지", p0.notification?.body.toString())

        val myHandler = Handler(Looper.getMainLooper())

//        메인 쓰레드 - UI쓰레드를 돌아주는 myHandler를 통해 토스트 출력

        myHandler.post {

            Toast.makeText(applicationContext, p0.notification?.title.toString(), Toast.LENGTH_SHORT).show()

        }

    }


}