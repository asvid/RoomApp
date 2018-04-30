package asvid.github.io.roomapp.utils

import java.util.*

object RandomStringGenerator {

    val baseString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam luctus porttitor elit, sit amet facilisis diam eleifend quis. Quisque elementum diam enim, nec condimentum lorem rhoncus ut. Maecenas sodales sem velit, at vulputate mauris imperdiet a. Integer leo mi, scelerisque lacinia rutrum ac, auctor pretium orci. Mauris aliquam euismod hendrerit. In sed mauris quis mi efficitur accumsan. Donec pretium sollicitudin sem, in egestas lectus mattis id. Sed finibus sollicitudin augue sit amet molestie. Nullam tempor eros ut molestie egestas. Mauris libero tortor, dictum nec porttitor quis, posuere quis libero. Proin scelerisque magna et iaculis aliquam. Curabitur mauris tellus, suscipit sed mi at, mattis venenatis turpis. Nulla sollicitudin sem eu venenatis vestibulum.\n" +
            "\n" +
            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent gravida velit mattis mi vehicula, eget accumsan est rhoncus. Vestibulum molestie nec elit nec luctus. Mauris in imperdiet neque. Nulla ac nibh ac risus vulputate accumsan. Proin scelerisque, arcu vulputate blandit lacinia, purus velit lobortis arcu, rhoncus ultricies mi erat nec dui. Integer odio nisl, ornare vitae vestibulum maximus, ultrices in lorem. Morbi eget volutpat enim. Integer fringilla convallis rhoncus. Sed eget placerat urna. Vestibulum elementum nulla ut sapien aliquam, non hendrerit sapien elementum. Aenean tellus leo, lacinia ut felis at, sagittis ornare justo. Donec at dignissim urna. Phasellus congue magna vulputate purus ullamcorper, a vestibulum velit vulputate. Nam et iaculis augue. Nunc euismod, metus nec dapibus ornare, nulla magna volutpat mauris, vitae faucibus neque neque quis turpis.\n" +
            "\n" +
            "Morbi mollis aliquam ipsum, non aliquet ligula sagittis a. Sed non nibh luctus, sollicitudin nunc eget, mollis nibh. Maecenas luctus luctus tortor at pellentesque. Fusce eu tempor leo. Ut a aliquam turpis, id aliquam arcu. Aenean maximus metus et mi pellentesque dignissim. Ut placerat molestie sodales. Aliquam erat volutpat.\n" +
            "\n" +
            "Maecenas sit amet neque bibendum ante condimentum porta sit amet quis lorem. Nulla vehicula id diam et egestas. Nam at erat in nibh tincidunt porta nec in metus. Nulla et justo eu orci consequat fringilla a sit amet nulla. Quisque nisi ante, iaculis quis venenatis in, facilisis ut leo. Aliquam erat volutpat. Nullam at varius libero, vel molestie sem. Nam fringilla ante enim, a viverra elit aliquam a. Curabitur nec ullamcorper lectus. Donec auctor at erat quis bibendum.\n" +
            "\n" +
            "Nulla ut faucibus justo. Etiam erat ipsum, dictum id erat a, consequat bibendum ligula. Nam pharetra ex vitae purus posuere convallis. Integer efficitur hendrerit arcu, vel consectetur ipsum. Donec non.\n" +
            "\n"

    fun getString(): String{
        val stringList = baseString.split(" ", limit = 0)
        val rnd = Random()
        val i = rnd.nextInt(stringList.size)
        return stringList[i]
    }

}