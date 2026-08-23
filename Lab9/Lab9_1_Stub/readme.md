# Lab#9.1 – Test Double: Stub (Movie NowPlaying)

## โครงสร้างโปรเจกต์
```
src/main/java/com/sqa/lab9/movie/
├── Movie.java                    # Model: title, cinemaType, showtime
├── MovieService.java             # <<interface>> dependency ของ NowPlaying
├── MoviePortalMovieService.java  # production impl ที่คุยกับ <<external>> MoviePortal จริง
└── NowPlaying.java               # SUT: กรองรายการหนังตาม cinemaType

src/test/java/com/sqa/lab9/movie/
├── MovieServiceStub.java         # Stub แทน MovieService (ข้อมูล hard-code)
└── NowPlayingTest.java           # ทดสอบ NowPlaying โดยฉีด Stub เข้าไป
```

## แนวคิด
- `NowPlaying` (SUT) ขึ้นกับ `MovieService` (interface) เท่านั้น ไม่ได้ผูกกับ `MoviePortalMovieService` โดยตรง (Dependency Inversion) จึงสามารถสลับ implementation ตอนเทสได้
- `MovieServiceStub` คือ Stub ที่เขียนขึ้นเอง (ไม่ใช้ Mockito) คืนค่าข้อมูลตายตัวเหมือนโจทย์: "The Odyssey, Spider-Man: Brand New Day, The End of Oak Street" สำหรับโรง IMAX with Laser และเพิ่มข้อมูล VIP/Standard เพื่อทดสอบ activity 9.1(3) — กรองเฉพาะ VIP cinema

## วิธีรัน
```bash
mvn test
```

## ขยายงานต่อ (ถ้าต้องการ)
- เพิ่ม implementation จริงใน `MoviePortalMovieService` ให้เรียก REST API ของ MoviePortal จริง (ปัจจุบัน throw `UnsupportedOperationException` ไว้ก่อน เพราะ lab เน้นที่ Test Double ไม่ใช่การเชื่อมระบบจริง)
