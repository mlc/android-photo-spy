Photo Spy for Android
=====================

This is a small program to display the list of photos on your Android
device and then to display those photos to you. The interesting thing
is that it requires absolutely no system permissions to be able to do
so.

This app does not have [`INTERNET` permission][internet]. So it can
not send your images to anyone and is not a “true” spy. But given that
almost every Android app in the Market does in fact have `INTERNET`
permission, this is not much of a limitation.

According [an article][nyt] in the _Times_, iOS needs location permission
to be able to do the same, which is kind of confusing. Google
“declined to comment on how its Android operating system for mobile
devices handles this issue,” which is kind of wise under the
circumstances.


Installation
------------

Scan this QR code:

![QR Code][qrcode]


Building
--------

Get the [Android SDK][sdk]. Then, fetch a copy of this project, open
up a terminal in the project's directory, and do

```
android update project -p .
ant clean debug
```

If you have a phone plugged in or an emulator running, you can then do

```
ant installd
```

to install.

License
-------

Copyright © 2012 [Michael Castleman][me].

This program is free software. It comes without any warranty, to
the extent permitted by applicable law. You can redistribute it
and/or modify it under the terms of the [Do What The Fuck You Want
To Public License][wtfpl], Version 2, as published by Sam Hocevar.

This project uses and distributes the [Android Compatibility
Library][support], which is Copyright The Android Open Source Project
and distributed under the [Apache License, Version 2.0][apache].

[internet]: http://developer.android.com/reference/android/Manifest.permission.html#INTERNET
[nyt]: http://bits.blogs.nytimes.com/2012/02/28/tk-ios-gives-developers-access-to-photos-videos-location/?hp
[qrcode]: https://chart.googleapis.com/chart?cht=qr&chs=300x300&chl=https://github.com/downloads/mlc/android-photo-spy/PhotoSpy-debug.apk
[sdk]: http://developer.android.com/sdk/index.html
[me]: http://mlcastle.net/
[wtfpl]: http://sam.zoy.org/wtfpl/
[support]: http://developer.android.com/sdk/compatibility-library.html
[apache]: https://www.apache.org/licenses/LICENSE-2.0
