# Lab#9.2 – Test Double: Mockito (Seat Reservation & GateCheckin)

## โครงสร้างโปรเจกต์
```
src/main/java/com/sqa/lab9/ticket/
├── Ticket.java                   # Model: ตั๋วที่ลูกค้าถือ
├── ReservationResult.java        # Model: ผลการจอง (เลขการจอง + ที่นั่ง)
├── CheckinResult.java            # Model: ผลการตรวจตั๋วหน้าประตู
├── SeatInventory.java            # <<interface>> dependency ของ SeatReservationService
├── TicketScanRegistry.java       # <<interface>> dependency ของ GateCheckinService
├── SeatReservationService.java   # SUT #1: ตรวจ/จองที่นั่ง
└── GateCheckinService.java       # SUT #2: ตรวจตั๋วหน้าประตู + นับจำนวนผู้เข้าชม

src/test/java/com/sqa/lab9/ticket/
├── SeatReservationServiceTest.java  # (a) mock หมายเลขที่นั่งว่าง
└── GateCheckinServiceTest.java      # (b) mock จำนวนผู้ผ่านการตรวจตั๋ว
```

## แนวคิด
ทั้งสอง Service คุยกับ **Ticket System** (ภายนอก) ผ่าน interface สองตัวที่แยกความรับผิดชอบกันชัดเจน:

- `SeatInventory` — ผังที่นั่ง (screen plan): เช็คที่นั่งว่าง, mark ที่นั่งว่าจอง, generate เลขการจอง
- `TicketScanRegistry` — บันทึกและนับจำนวนการสแกนตั๋วหน้าประตู แยกตาม (movieId, hallType, date) เพื่อใช้เทียบกับยอดขายตั๋ว ป้องกันลูกค้าตั๋วโรงราคาถูกเข้าโรงราคาแพง

ทดสอบด้วย **Mockito** (`@Mock` + `@ExtendWith(MockitoExtension.class)`) แทนที่จะเขียน Stub มือ เพื่อ:
1. Stub ค่าที่ต้องการด้วย `when(...).thenReturn(...)`
2. ตรวจสอบ interaction ด้วย `verify(...)` เช่น ต้องไม่ไปเรียก `reserveSeat` ถ้าที่นั่งไม่ว่างตั้งแต่แรก, ต้องไม่ `recordScan` ถ้าตั๋วผิดโรง

## วิธีรัน
```bash
mvn test
```

## หมายเหตุ
โจทย์อ้างอิงถึง repo ตัวอย่าง `https://github.com/ChitsuthaCSKKU/SQA/tree/2026/LabAssignment/Lab9_TestDouble` — แนะนำให้ clone มาเทียบโครงสร้าง class ที่อาจารย์กำหนดไว้จริง แล้วปรับชื่อ class/method ในโปรเจกต์นี้ให้ตรงกันก่อนส่งงาน (ระบบไม่สามารถเข้าถึง repo ดังกล่าวได้ในการสร้างโค้ดชุดนี้ จึงออกแบบ class ตามคำอธิบายใน spec แทน)
