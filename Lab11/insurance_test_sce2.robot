*** Settings ***
Documentation    UAT-Lab11-002: การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ

Library          SeleniumLibrary

Test Setup       เปิดเบราว์เซอร์เข้าสู่ระบบ
Test Teardown    Close Browser


*** Variables ***
${URL}                     http://sampleapp.tricentis.com/
${BROWSER}                 chrome
${TIMEOUT}                 10s
${MENU_AUTOMOBILE}         id=nav_automobile
${MAKE}                    id=make
${BTN_NEXT_INSURANT}       id=nextenterinsurantdata
${TAB_INSURANT}            id=enterinsurantdata
${BTN_NEXT_PRODUCT}        id=nextenterproductdata
${TAB_PRODUCT}             id=enterproductdata
${BTN_NEXT_PRICE}          id=nextselectpriceoption
${SELECT_SILVER}           id=selectsilver
${VEHICLE_TAB_COUNTER}     xpath=//a[@id='entervehicledata']//span[contains(@class,'counter')]
${INSURANT_TAB_COUNTER}    xpath=//a[@id='enterinsurantdata']//span[contains(@class,'counter')]
${PRODUCT_TAB_COUNTER}     xpath=//a[@id='enterproductdata']//span[contains(@class,'counter')]


*** Test Cases ***
UAT-Lab11-002 การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ
    [Documentation]    กรอกข้อมูลไม่สมบูรณ์/ไม่ถูกต้องเพื่อทดสอบการแจ้งเตือนและไม่สามารถไปหน้าเลือก Package ได้
    เปิดหน้าประกันภัยรถยนต์
    ข้ามไปยังหน้าข้อมูลผู้เอาประกัน
    ข้ามไปยังหน้าข้อมูลผลิตภัณฑ์
    ตรวจสอบว่าไม่สามารถเลือก Package ได้


*** Keywords ***
เปิดเบราว์เซอร์เข้าสู่ระบบ
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window

เปิดหน้าประกันภัยรถยนต์
    Wait Until Page Contains Element    ${MENU_AUTOMOBILE}    timeout=${TIMEOUT}
    Wait Until Element Is Visible    ${MENU_AUTOMOBILE}    timeout=${TIMEOUT}
    Click Element    ${MENU_AUTOMOBILE}
    Wait Until Element Is Visible    ${MAKE}    timeout=${TIMEOUT}

ข้ามไปยังหน้าข้อมูลผู้เอาประกัน
    Wait Until Element Is Visible    ${BTN_NEXT_INSURANT}    timeout=${TIMEOUT}
    Wait Until Element Is Enabled    ${BTN_NEXT_INSURANT}    timeout=${TIMEOUT}
    Click Element    ${BTN_NEXT_INSURANT}
    Wait Until Page Contains Element
    ...    ${VEHICLE_TAB_COUNTER}
    ...    timeout=${TIMEOUT}
    Run Keyword And Ignore Error    Click Element    ${TAB_INSURANT}

ข้ามไปยังหน้าข้อมูลผลิตภัณฑ์
    Run Keyword And Ignore Error    Click Element    ${BTN_NEXT_PRODUCT}
    Wait Until Page Contains Element
    ...    ${INSURANT_TAB_COUNTER}
    ...    timeout=${TIMEOUT}
    Run Keyword And Ignore Error    Click Element    ${TAB_PRODUCT}

ตรวจสอบว่าไม่สามารถเลือก Package ได้
    Run Keyword And Ignore Error    Click Element    ${BTN_NEXT_PRICE}
    Wait Until Page Contains Element
    ...    ${PRODUCT_TAB_COUNTER}
    ...    timeout=${TIMEOUT}
    Element Should Not Be Visible    ${SELECT_SILVER}
