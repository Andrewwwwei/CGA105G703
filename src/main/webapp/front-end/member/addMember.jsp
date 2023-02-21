<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date , java.text.SimpleDateFormat"%>
<%
SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
Date currentTime_1 = new Date();
String pDate = formatter.format(currentTime_1); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/form.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

    <title>會員註冊</title>
</head>
<style>

</style>

<body>
    <input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號"
        style="display:none" />
        
<!-- ENCTYPE="multipart/form-data" -->
<%-- action="<%=request.getContextPath()%>/usersServlet" --%>
    <form id="login" method="post" action="<%=request.getContextPath()%>/usersServlet"  name="login" ENCTYPE="multipart/form-data">
    <input type="hidden" name="action" value="addMember">
        <h2 id="user_login">會員註冊</h2>
        <!-- ====================================================================================================== 頁面信箱-->
        <div id="user_Email_border">
            <!--外框-->
            <label for="user_Email">
                <!--label-->
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-envelope-fill" viewBox="0 0 16 16">
                        <path
                            d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555ZM0 4.697v7.104l5.803-3.558L0 4.697ZM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757Zm3.436-.586L16 11.801V4.697l-5.803 3.546Z" />
                    </svg>
                </div>
                <!--svg符號-->
                <input id="user_Email" type="text" name=userAccount placeholder="請輸入電子信箱" maxlength="40"
                    title="帳號必須由6~40字元的英文加數字組成" autofocus>
                <!--input按鈕-->
            </label>
            <div id="user_Email_After_block"></div>
            <!--偽元素提示-->
            <div id="user_Email_After"></div>
            <!--偽元素錯誤提示-->
        </div>
        <!-- ====================================================================================================== 頁面密碼-->
        <div id="user_Password_border">
            <label for="user_Password">
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-file-ppt" viewBox="0 0 16 16">
                        <path
                            d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm10-1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1z" />
                        <path
                            d="M6 5a1 1 0 0 1 1-1h1.188a2.75 2.75 0 0 1 0 5.5H7v2a.5.5 0 0 1-1 0V5zm1 3.5h1.188a1.75 1.75 0 1 0 0-3.5H7v3.5z" />
                    </svg>
                </div>
                <input id="user_Password" type="password" name="userPassword" placeholder="請輸入密碼" maxlength="15" title="密碼必須由6~15字元的英文加數字組成"
                    autofocus>
            </label>
            <div id="user_Password_After_block"></div>
            <div id="user_Password_After"></div>
        </div>
        <!-- ====================================================================================================== 頁面再輸入密碼-->

        <div id="user_Password_angin_border">
            <label for="user_Password_angin">
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-file-ppt-fill" viewBox="0 0 16 16">
                        <path d="M8.188 8.5H7V5h1.188a1.75 1.75 0 1 1 0 3.5z" />
                        <path
                            d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm3 4a1 1 0 0 0-1 1v6.5a.5.5 0 0 0 1 0v-2h1.188a2.75 2.75 0 0 0 0-5.5H7z" />
                    </svg>
                </div>
                <input id="user_Password_angin" type="password" name="userPasswordangin" placeholder="請再次輸入密碼" maxlength="15"
                    title="密碼必須和上一欄數值一樣" autofocus>
            </label>
            <div id="user_Password_angin_After_block"></div>
            <div id="user_Password_angin_After"></div>
        </div>
        <!-- ====================================================================================================== 姓名-->

        <div id="user_Name_border">
            <label for="user_Name">
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-person-vcard-fill" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                            d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm9 1.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5ZM9 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4A.5.5 0 0 1 9 8Zm1 2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5Zm-1 2c0 .17-.014.337-.04.5H2a1 1 0 0 1-.984-.819C1.2 10.398 2.914 9 5 9c2.21 0 4 1.567 4 3.5ZM7 6a2 2 0 1 1-4 0 2 2 0 0 1 4 0Z" />
                    </svg>
                </div>
                <input id="user_Name" type="text" name=userName placeholder="請輸入姓名" maxlength="8" title="姓名必須由2~8個中文字組成" autofocus>
            </label>
            <div id="user_Name_After_block"></div>
            <div id="user_Name_After"></div>
        </div>
        <!-- ====================================================================================================== 暱稱-->

        <div id="user_NickName_border">
            <label for="user_NickName">
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                        <path fill-rule="evenodd"
                            d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
                    </svg>
                </div>
                <input id="user_NickName" type="text" name=userNickname maxlength="10" placeholder="請輸入暱稱"
                    title="匿名必須由1~10個中文字、數字、符號、英文組成" autofocus name="USER">
            </label>
            <div id="user_NickName_After_block"></div>
        </div>
        <!-- ====================================================================================================== 身分證-->

        <div id="user_ID_Number_border">
            <label for="user_ID_Number">
                <div><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-fingerprint" viewBox="0 0 16 16">
                        <path
                            d="M8.06 6.5a.5.5 0 0 1 .5.5v.776a11.5 11.5 0 0 1-.552 3.519l-1.331 4.14a.5.5 0 0 1-.952-.305l1.33-4.141a10.5 10.5 0 0 0 .504-3.213V7a.5.5 0 0 1 .5-.5Z" />
                        <path
                            d="M6.06 7a2 2 0 1 1 4 0 .5.5 0 1 1-1 0 1 1 0 1 0-2 0v.332c0 .409-.022.816-.066 1.221A.5.5 0 0 1 6 8.447c.04-.37.06-.742.06-1.115V7Zm3.509 1a.5.5 0 0 1 .487.513 11.5 11.5 0 0 1-.587 3.339l-1.266 3.8a.5.5 0 0 1-.949-.317l1.267-3.8a10.5 10.5 0 0 0 .535-3.048A.5.5 0 0 1 9.569 8Zm-3.356 2.115a.5.5 0 0 1 .33.626L5.24 14.939a.5.5 0 1 1-.955-.296l1.303-4.199a.5.5 0 0 1 .625-.329Z" />
                        <path
                            d="M4.759 5.833A3.501 3.501 0 0 1 11.559 7a.5.5 0 0 1-1 0 2.5 2.5 0 0 0-4.857-.833.5.5 0 1 1-.943-.334Zm.3 1.67a.5.5 0 0 1 .449.546 10.72 10.72 0 0 1-.4 2.031l-1.222 4.072a.5.5 0 1 1-.958-.287L4.15 9.793a9.72 9.72 0 0 0 .363-1.842.5.5 0 0 1 .546-.449Zm6 .647a.5.5 0 0 1 .5.5c0 1.28-.213 2.552-.632 3.762l-1.09 3.145a.5.5 0 0 1-.944-.327l1.089-3.145c.382-1.105.578-2.266.578-3.435a.5.5 0 0 1 .5-.5Z" />
                        <path
                            d="M3.902 4.222a4.996 4.996 0 0 1 5.202-2.113.5.5 0 0 1-.208.979 3.996 3.996 0 0 0-4.163 1.69.5.5 0 0 1-.831-.556Zm6.72-.955a.5.5 0 0 1 .705-.052A4.99 4.99 0 0 1 13.059 7v1.5a.5.5 0 1 1-1 0V7a3.99 3.99 0 0 0-1.386-3.028.5.5 0 0 1-.051-.705ZM3.68 5.842a.5.5 0 0 1 .422.568c-.029.192-.044.39-.044.59 0 .71-.1 1.417-.298 2.1l-1.14 3.923a.5.5 0 1 1-.96-.279L2.8 8.821A6.531 6.531 0 0 0 3.058 7c0-.25.019-.496.054-.736a.5.5 0 0 1 .568-.422Zm8.882 3.66a.5.5 0 0 1 .456.54c-.084 1-.298 1.986-.64 2.934l-.744 2.068a.5.5 0 0 1-.941-.338l.745-2.07a10.51 10.51 0 0 0 .584-2.678.5.5 0 0 1 .54-.456Z" />
                        <path
                            d="M4.81 1.37A6.5 6.5 0 0 1 14.56 7a.5.5 0 1 1-1 0 5.5 5.5 0 0 0-8.25-4.765.5.5 0 0 1-.5-.865Zm-.89 1.257a.5.5 0 0 1 .04.706A5.478 5.478 0 0 0 2.56 7a.5.5 0 0 1-1 0c0-1.664.626-3.184 1.655-4.333a.5.5 0 0 1 .706-.04ZM1.915 8.02a.5.5 0 0 1 .346.616l-.779 2.767a.5.5 0 1 1-.962-.27l.778-2.767a.5.5 0 0 1 .617-.346Zm12.15.481a.5.5 0 0 1 .49.51c-.03 1.499-.161 3.025-.727 4.533l-.07.187a.5.5 0 0 1-.936-.351l.07-.187c.506-1.35.634-2.74.663-4.202a.5.5 0 0 1 .51-.49Z" />
                    </svg>
                </div>
                <input id="user_ID_Number" type="text" name=userIdNo maxlength="10" placeholder="請輸入身分證字號"
                    title="身分證字號必須由10字元的英文加數字組成" autofocus>
            </label>
            <div id="user_ID_Number_After_block"></div>
            <div id="user_ID_Number_After"></div>
        </div>
        <!-- ====================================================================================================== 生日-->

        <div id="user_Birthday_border">
            <label for="user_Birthday">
                <div id="div">

                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256" id="IconChangeColor" height="20"
                        width="20">
                        <rect width="256" height="256" fill="none"></rect>
                        <line x1="128" y1="88" x2="128" y2="64" fill="none" stroke="#000" stroke-linecap="round"
                            stroke-linejoin="round" stroke-width="12"></line>
                        <path d="M128,64c46.2-16,0-56,0-56S80,48,128,64Z" fill="none" stroke="#000"
                            stroke-linecap="round" stroke-linejoin="round" stroke-width="12" id="mainIconPathAttribute">
                        </path>
                        <path d="M162,126a34,34,0,0,1-68,0" fill="none" stroke="#000" stroke-linecap="round"
                            stroke-linejoin="round" stroke-width="12" id="mainIconPathAttribute"></path>
                        <path
                            d="M94,126a34,34,0,0,1-33.3,34c-19.1.4-34.7-15.6-34.7-34.7V112A23.9,23.9,0,0,1,50,88H206a23.9,23.9,0,0,1,24,24v13.3c0,19.1-15.6,35.1-34.7,34.7A34,34,0,0,1,162,126"
                            fill="none" stroke="#000" stroke-linecap="round" stroke-linejoin="round" stroke-width="12"
                            id="mainIconPathAttribute"></path>
                        <path d="M216,153.3V208a8,8,0,0,1-8,8H48a8,8,0,0,1-8-8V153.3" fill="none" stroke="#000"
                            stroke-linecap="round" stroke-linejoin="round" stroke-width="12" id="mainIconPathAttribute">
                        </path>
                    </svg>
                    <div id="text">請輸入生日</div>
                </div><input id="user_Birthday" type="date" name=userBrthday title=" 生日可以手動與選擇輸入" autofocus max="<%=pDate%>">
            </label>
            <div id="user_Birthday_After_block"></div>
            <div id="user_Birthday_After"></div>
        </div>
        <!-- ====================================================================================================== 手機-->

        <div id="user_Phone_border">
            <label for="user_ID_Number">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-phone-vibrate" viewBox="0 0 16 16">
                        <path
                            d="M10 3a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4zM6 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H6z" />
                        <path
                            d="M8 12a1 1 0 1 0 0-2 1 1 0 0 0 0 2zM1.599 4.058a.5.5 0 0 1 .208.676A6.967 6.967 0 0 0 1 8c0 1.18.292 2.292.807 3.266a.5.5 0 0 1-.884.468A7.968 7.968 0 0 1 0 8c0-1.347.334-2.619.923-3.734a.5.5 0 0 1 .676-.208zm12.802 0a.5.5 0 0 1 .676.208A7.967 7.967 0 0 1 16 8a7.967 7.967 0 0 1-.923 3.734.5.5 0 0 1-.884-.468A6.967 6.967 0 0 0 15 8c0-1.18-.292-2.292-.807-3.266a.5.5 0 0 1 .208-.676zM3.057 5.534a.5.5 0 0 1 .284.648A4.986 4.986 0 0 0 3 8c0 .642.12 1.255.34 1.818a.5.5 0 1 1-.93.364A5.986 5.986 0 0 1 2 8c0-.769.145-1.505.41-2.182a.5.5 0 0 1 .647-.284zm9.886 0a.5.5 0 0 1 .648.284C13.855 6.495 14 7.231 14 8c0 .769-.145 1.505-.41 2.182a.5.5 0 0 1-.93-.364C12.88 9.255 13 8.642 13 8c0-.642-.12-1.255-.34-1.818a.5.5 0 0 1 .283-.648z" />
                    </svg>
                </div>
                <input id="user_Phone" type="text" name=userPhone placeholder="請輸入手機號碼" title="電話號碼必須由10字元的數字組成" autofocus>
            </label>
            <div id="user_Phone_After_block"></div>
            <div id="user_Phone_After"></div>
        </div>
        <!-- ====================================================================================================== 地址-->

        <div id="user_Address_border">
            <label for="user_Address">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-house-fill" viewBox="0 0 16 16">
                        <path
                            d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z" />
                        <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z" />
                    </svg>

                </div>
                <select id="city" placeholder="請選擇縣市" name="city"></select>
                <select id="dist" placeholder="請選擇鄉鎮區"name="dist"></select>
                <input id="user_Address" type="text" name=Address placeholder="請輸入地址" title="地址必須由10~30字元的中文、英文、數字組成" autofocus>
            </label>
            <div id="user_Address_After_block"></div>
            <div id="user_Address_After"></div>
        </div>
        <!-- ====================================================================================================== 性別-->

        <div id="user_Gender_border">
            <label for="user_Gender">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-emoji-laughing" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                        <path
                            d="M12.331 9.5a1 1 0 0 1 0 1A4.998 4.998 0 0 1 8 13a4.998 4.998 0 0 1-4.33-2.5A1 1 0 0 1 4.535 9h6.93a1 1 0 0 1 .866.5zM7 6.5c0 .828-.448 0-1 0s-1 .828-1 0S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 0-1 0s-1 .828-1 0S9.448 5 10 5s1 .672 1 1.5z" />
                    </svg>

                </div>
                <select id="user_Gender" name=userGender>
                    <option>未選擇</option>
                    <option>男性</option>
                    <option>女性</option>
                </select>
            </label>
            <div id="user_Gender_After_block"></div>
        </div>
        <!-- ====================================================================================================== 頭像-->

        <div id="user_file_border">
            <label for="user_file">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-person-square" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                        <path
                            d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12z" />
                    </svg>

                </div>
                <label id="btn">
                    <div id="text">請輸入頭像</div><input id="user_file" type="file" name=userPic title="頭像需小於16mb"
                        accept="image/gif, image/jpeg, image/png">
                </label><img id="preview_img" />
            </label>
            <div id="user_file_After_block"></div>
        </div>    
         <input id="login_Submit" type="button" value="註冊送出" onClick="check()">
    </form>
        <!-- ====================================================================================================== -->

    

</body>
<script>
    (function ($) {
        // Here “$" is a jQuery reference
    })
</script>
<script>
    //==============================================================錯誤訊息隱藏
    let pass1 = document.getElementById("user_Email_After");
    pass1.style.display = "none";
    let pass2 = document.getElementById("user_Password_After");
    pass2.style.display = "none";
    let pass3 = document.getElementById("user_Password_angin_After");
    pass3.style.display = "none";
    let pass4 = document.getElementById("user_ID_Number_After");
    pass4.style.display = "none";
    let pass5 = document.getElementById("user_Birthday_After");
    pass5.style.display = "none";
    let pass6 = document.getElementById("user_Name_After");
    pass6.style.display = "none";
    let pass7 = document.getElementById("user_Phone_After");
    pass7.style.display = "none";
    let pass27 = document.getElementById("user_Address_After");
    pass27.style.display = "none";

    //==============================================================輸入指引訊息
    //=============================信箱
    let pass15 = document.getElementById("user_Email_After_block");//輸入指引訊息div外框
    pass15.style.display = "none";
    let pass8 = document.getElementById("user_Email").addEventListener('click', function () {//input onfocus
        pass15.style.display = "block";
    }, true);
    pass8 = document.getElementById("user_Email").addEventListener('blur', function () {//input blur
        pass15.style.display = "none";
    }, true);
    //=============================密碼
    let pass16 = document.getElementById("user_Password_After_block");
    pass16.style.display = "none";
    let pass9 = document.getElementById("user_Password").addEventListener('click', function () {
        pass16.style.display = "block";
    }, true);
    pass9 = document.getElementById("user_Password").addEventListener('blur', function () {
        pass16.style.display = "none";
    }, true);
    //=============================再次輸入密碼
    let pass17 = document.getElementById("user_Password_angin_After_block");
    pass17.style.display = "none";
    let pass10 = document.getElementById("user_Password_angin").addEventListener('click', function () {
        pass17.style.display = "block";
    }, true);
    pass10 = document.getElementById("user_Password_angin").addEventListener('blur', function () {
        pass17.style.display = "none";
    }, true);
    //=============================輸入姓名
    let pass18 = document.getElementById("user_Name_After_block");
    pass18.style.display = "none";
    let pass13 = document.getElementById("user_Name").addEventListener('click', function () {
        pass18.style.display = "block";
    }, true);
    pass13 = document.getElementById("user_Name").addEventListener('blur', function () {
        pass18.style.display = "none";
    }, true);
    //=============================輸入暱稱
    let pass19 = document.getElementById("user_NickName_After_block");
    pass19.style.display = "none";
    let pass26 = document.getElementById("user_NickName").addEventListener('click', function () {
        pass19.style.display = "block";
    }, true);
    pass26 = document.getElementById("user_NickName").addEventListener('blur', function () {
        pass19.style.display = "none";
    }, true);
    //=============================輸入身分證
    let pass20 = document.getElementById("user_ID_Number_After_block");
    pass20.style.display = "none";
    let pass11 = document.getElementById("user_ID_Number").addEventListener('click', function () {
        pass20.style.display = "block";
    }, true);
    pass11 = document.getElementById("user_ID_Number").addEventListener('blur', function () {
        pass20.style.display = "none";
    }, true);
    //=============================輸入生日
    let pass21 = document.getElementById("user_Birthday_After_block");
    pass21.style.display = "none";
    let pass12 = document.getElementById("user_Birthday").addEventListener('click', function () {
        pass21.style.display = "block";
    }, true);
    pass12 = document.getElementById("user_Birthday").addEventListener('blur', function () {
        pass21.style.display = "none";
    }, true);
    //=============================輸入電話
    let pass22 = document.getElementById("user_Phone_After_block");
    pass22.style.display = "none";
    let pass14 = document.getElementById("user_Phone").addEventListener('click', function () {
        pass22.style.display = "block";
    }, true);
    pass14 = document.getElementById("user_Phone").addEventListener('blur', function () {
        pass22.style.display = "none";
    }, true);
    //=============================輸入地址
    let pass28 = document.getElementById("user_Address_After_block");
    pass28.style.display = "none";
    let pass29 = document.getElementById("user_Address").addEventListener('click', function () {
        pass28.style.display = "block";
    }, true);
    pass29 = document.getElementById("user_Address").addEventListener('blur', function () {
        pass28.style.display = "none";
    }, true);
    //=============================輸入性別
    let pass25 = document.getElementById("user_Gender_After_block");
    pass25.style.display = "none";
    let pass30 = document.getElementById("user_Gender").addEventListener('click', function () {
        pass25.style.display = "block";
    }, true);
    pass30 = document.getElementById("user_Gender").addEventListener('blur', function () {
        pass25.style.display = "none";
    }, true);
    //=============================輸入頭像
    let pass31 = document.getElementById("user_file_After_block");
    pass31.style.display = "none";
    let pass32 = document.getElementById("user_file").addEventListener('click', function () {
        pass31.style.display = "block";
    }, true);
    // pass32 = document.getElementById("user_file").addEventListener('blur', function () {
    //     pass31.style.display = "none";
    // }, true);
    //==============================================================點擊選項錯誤訊息隱藏
    pass8 = document.getElementById("user_Email").addEventListener('click', function () {
        pass1.style.display = "none";
    }, true);
    pass9 = document.getElementById("user_Password").addEventListener('click', function () {
        pass2.style.display = "none";
    }, true);
    pass10 = document.getElementById("user_Password_angin").addEventListener('click', function () {
        pass3.style.display = "none";
    }, true);
    pass11 = document.getElementById("user_ID_Number").addEventListener('click', function () {
        pass4.style.display = "none";
    }, true);
    pass12 = document.getElementById("user_Birthday").addEventListener('click', function () {
        pass5.style.display = "none";
    }, true);
    pass13 = document.getElementById("user_Name").addEventListener('click', function () {
        pass6.style.display = "none";
    }, true);
    pass14 = document.getElementById("user_Phone").addEventListener('click', function () {
        pass7.style.display = "none";
    }, true);
    pass29 = document.getElementById("user_Address").addEventListener('click', function () {
        pass27.style.display = "none";
    }, true);
    //==============================================================外框發亮
    //=============================信箱
    let user_Email_border = document.querySelector("#user_Email_border");//div外框
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Email") {//input

            user_Email_border.className = "change";//前面宣告變數名，後面特效css
        } else {
            user_Email_border.className = "user_Email_border";//div外框
        }
    });
    //=============================密碼
    let user_Password_border = document.querySelector("#user_Password_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Password") {

            user_Password_border.className = "change";
        } else {
            user_Password_border.className = "user_Password_border";
        }
    });
    //=============================再次輸入密碼
    let user_Password_angin_border = document.querySelector("#user_Password_angin_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Password_angin") {

            user_Password_angin_border.className = "change";
        } else {
            user_Password_angin_border.className = "user_Password_angin_border";
        }
    });
    //=============================姓名
    let user_Name_border = document.querySelector("#user_Name_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Name") {
            user_Name_border.className = "change";
        } else {
            user_Name_border.className = "user_Name_border";
        }
    });
    //=============================暱稱
    let user_NickName_border = document.querySelector("#user_NickName_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_NickName") {
            user_NickName_border.className = "change";
        } else {
            user_NickName_border.className = "user_NickName_border";
        }
    });
    //=============================身分證
    let user_ID_Number_border = document.querySelector("#user_ID_Number_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_ID_Number") {
            user_ID_Number_border.className = "change";
        } else {
            user_ID_Number_border.className = "user_ID_Number_border";
        }
    });
    //=============================電話號碼
    let user_Phone_border = document.querySelector("#user_Phone_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Phone") {
            user_Phone_border.className = "change";
        } else {
            user_Phone_border.className = "user_Phone_border";
        }
    });
    //=============================地址
    let user_Address_border = document.querySelector("#user_Address_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Address") {
            user_Address_border.className = "change";
        } else {
            user_Address_border.className = "user_Address_border";
        }
    });
    //=============================性別
    let user_Gender_border = document.querySelector("#user_Gender_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_Gender") {
            user_Gender_border.className = "change";
        } else {
            user_Gender_border.className = "user_Gender_border";
        }
    });
    //=============================頭像
    let user_file_border = document.querySelector("#user_file_border");
    document.addEventListener("click", function (e) {
        if (e.target.id == "user_file") {
            user_file_border.className = "change";
        } else {
            user_file_border.className = "user_file_border";
            pass31.style.display = "none";
        }
    });
    //==============================================================預覽頭像
    const myFile = document.querySelector('#user_file')
    myFile.addEventListener('change', function (e) {

        const img = document.querySelector('#preview_img');
        img.src = URL.createObjectURL(e.target.files[0]);
        img.innerHTML += `<input type="file" onchange="document.getElementById("preview_img").src = window.URL.createObjectURL(this.files[0])">
<img id="preview_img" >`;
    })
    //===============================================================信箱格式
    function checkEmail(item) {
        var email_pattern = /^.+@.+\..{2,3}$/;
        if (item.value == "") {
            pass1 = document.getElementById("user_Email_After");
            pass1.style.display = "block";
            item.focus();
            return false;
        }
        if (!email_pattern.test(item.value)) {
            pass1 = document.getElementById("user_Email_After");
            pass1.style.display = "block";
            item.focus();
            return false;
        }
        return true;
    }

    //================================================================密碼格式
    function checkUserpassword(item) {
        if (item.value == "") {
            let pass = document.getElementById("user_Password_After");
            pass.style.display = "block";
            item.focus();
            return false;
        }
        if (item.value.length < 4 || item.value.length > 8) {
            let pass = document.getElementById("user_Password_After");
            pass.style.display = "block";
            item.focus();
            return false;
        }
        var score_i = 0, score_c = 0;
        var l_str = unescape('%27') + unescape('%22') + unescape('%2A') + unescape('%20');
        //%27=', %22=", %2A=*, %20= (空白)
        //escape函數：字串編碼(傳回以ISO Latin字元集來表示參數的十六進制編碼)
        return true;
    }
    //================================================================再次輸入密碼格式
    function checkUserpasswordAgain(item) {
        if (item.value == "") {
            pass = document.getElementById("user_Password_angin_After");
            pass.style.display = "block";
            item.focus();
            return false;
        }
        if (document.login.user_Password.value != item.value) {
            pass = document.getElementById("user_Password_angin_After");
            pass.style.display = "block";
            item.value = "";
            item.focus();
            return false;
        }
        return true;
    }
    //================================================================姓名格式
    function checkUsername(item) {
        var rex = /^[\u4E00-\u9FA5]{2,8}$/;
        if (!rex.test(item.value)) {
            pass = document.getElementById("user_Name_After");
            pass.style.display = "block";
            item.focus();
            return false;
        } if (item.value == "") {
            pass = document.getElementById("user_Name_After");
            pass.style.display = "block";
            item.focus();
            return false;
        }
        return true;
    }
    //================================================================身分證格式
    function checkUserid(item) {
        tab = "ABCDEFGHJKLMNPQRSTUVXYWZIO"
        Ary1 = new Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3);
        Ary2 = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5);
        Mix = new Array(9, 8, 7, 6, 5, 4, 3, 2, 1, 1);
        let i;
        let v;
        let j;
        if (item.value.length != 10) {
            pass = document.getElementById("user_ID_Number_After");
            pass.style.display = "block";
            return false;
        }

        // j.toLowerCase();
        // alert(j);
        i = tab.indexOf(item.value.charAt(0).toUpperCase());
        if (i == -1) {
            pass = document.getElementById("user_ID_Number_After");
            pass.style.display = "block";
            return false;
        } //i=0
        sum = Ary1[i] + Ary2[i] * 9;
        //sum=1 

        for (i = 1; i < 10; i++) {
            v = parseInt(item.value.charAt(i));
            if (isNaN(v)) {
                pass = document.getElementById("user_ID_Number_After");
                pass.style.display = "block";
                return false;
            }
            sum = sum + v * Mix[i];
        }
        if (sum % 10 != 0) {
            pass = document.getElementById("user_ID_Number_After");
            pass.style.display = "block";
            return false;
        }
        return true;
    }
    //================================================================生日驗證
    function checkUserBirthday(item) {
        if (item.value == "") {
            pass = document.getElementById("user_Birthday_After");
            pass.style.display = "block";
            return false;
        }
        return true;
    }
    //================================================================電話驗證
    function checkPhone(item) {
        var iphone = /^[0-9]{10}$/;
        if (!(iphone.test(item.value))) {
            pass = document.getElementById("user_Phone_After");
            pass.style.display = "block";
            return false;
        }
        return true;
    }
    //=================================================================錯誤邏輯
    function check() {
        if (!checkEmail(document.login.user_Email)) { return false };
        if (!checkUserpassword(document.login.user_Password)) return false;
        if (!checkUserpasswordAgain(document.login.user_Password_angin)) return false;
        if (!checkUsername(document.login.user_Name)) return false;
        if (!checkUserid(document.login.user_ID_Number)) return false;
        if (!checkUserBirthday(document.login.user_Birthday)) return false;
        if (!checkPhone(document.login.user_Phone)) return false;
        // if (!checkBirth(document.reg)) return false;

        document.login.submit();
    }

</script>


</html>