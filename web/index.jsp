<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2018/12/28
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用WebSocket实时知比特币价格</title>

  </head>
  <body>
    <div style="width: 400px;margin: 20px auto;border: 1px solid lightgray;padding: 20px;text-align: center">
      当前比特币价格：￥<span style="color: #FF7519" id="price">10000</span>
      <div style="font-size: 0.9em;margin-top: 20px">查看的人越多，价格越高，当前总共<span id="total"></span>个人在线</div>
      <div style="color:silver;font-size:0.8em;margin-top:20px ">以上价格纯属虚构，如有雷同，再见</div>
    </div>
  </body>
  <script type="text/javascript">
    var websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket("ws://localhost:8080/bitcoin/ws/bitcoinServer");

        //连接成功建立的回调方法
        websocket.onopen=function(){
            websocket.send("客户端连接成功");
        }
        //接收到消息的回调函数
        websocket.onmessage=function(event){
            setMessageInnerHTML(event.data);
        }
        //连接发生错误的回调方法
        websocket.onerror=function(){
            alert("WebSocket连接发生错误");
        }
        //连接关闭的回调函数
        websocket.onclose=function(){
            alert("WebSocket连接关闭");
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
        //防止连接还没断开就关闭窗口，server端会抛异常
        window.onbeforeunload=function () {
            closeWebSocket();
        }
    }
    else{
        alert("当前浏览器不支持WebSocket");
    }
    function setMessageInnerHTML(innerHTML){
        var bitcoin=eval("("+innerHTML+")");
        document.getElementById("price").innerHTML=bitcoin.price;
        document.getElementById("total").innerHTML=bitcoin.total;
    }
    function closeWebSocket() {
        websocket.close();
    }

  </script>
</html>
