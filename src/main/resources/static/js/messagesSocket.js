var stompClient = null;

var roomId = null;

function setConnected(connected) {
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
}

function connect() {
    var socket = new SockJS('/messages');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (greeting) {
            showMessage(JSON.parse(greeting.body).messageText, JSON.parse(greeting.body).author);
        });
    });
}


function disconnect() {
    document.getElementById('messageText').value = '';
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

connect();

function getRoom() {
    roomId = document.getElementsByClassName('active')[0].id.replace('r', '');
    return roomId;
}

//todo на фронте есть список комнат, у каждой комнате есть свой id
//todo и вот по этому id нужно показывать сообщения, которые есть в комнате и отправлять сообщение в комнату с этим id
//todo при клике на комнату отрисовывать все сообщений из этой комнаты, механизм отправки сообщения просто доработать, отправляя id на бек
function sendMessage() {
    var messageText = document.getElementById('messageText').value;
    if (messageText.trim() !== '') {
        var messageChatArray = messageText.split(' ');
        if (messageChatArray[0].split('')[0] + messageChatArray[0].split('')[1] !== '//') {
            document.getElementById('messageText').value = '';
            document.getElementById('messageError').innerHTML = '';
            stompClient.send("/app/messages", {}, JSON.stringify({
                'messageText': messageText,
                'room': {
                    'id': getRoom()
                }
            }));
        } else {
            messageChatArray[0] = messageChatArray[0].replace('/', '').replace('/', '');
            // команды для комнаты
            if (messageChatArray[0] === 'room') {
                roomCommands(messageChatArray);
            } else if (messageChatArray[0] === 'user') {
                userCommand(messageChatArray);
            }
        }

    } else {
        document.getElementById('messageError').innerHTML = 'Write your message';
    }
}

function showMessage(messageText, author) {
    var response = document.getElementById('response' + roomId);
    var row = document.createElement("div");
    row.className = 'row';
    var divCol = document.createElement("div");
    divCol.className = 'col';
    var divW100 = document.createElement("div");
    divW100.className = 'w-100';
    var divColAuthor = document.createElement("div");
    divColAuthor.className = 'col';
    var pAuthor = document.createElement("p");
    var divColDateTime = document.createElement("div");
    divColDateTime.className = 'col';
    var spanDateTime = document.createElement("span");
    var pDateTime = document.createElement("p");
    pDateTime.style = 'text-align:left';
    var divColMessageText = document.createElement("div");
    divColMessageText.className = 'col';
    var pMessageText = document.createElement("p");
    var br = document.createElement("br");

    response.appendChild(row);
    row.appendChild(divCol);
    divCol.appendChild(divW100);
    divCol.appendChild(divColAuthor);
    divColAuthor.appendChild(pAuthor);
    pAuthor.appendChild(document.createTextNode(author.username));
    divCol.appendChild(divColDateTime);
    divColDateTime.appendChild(spanDateTime);
    spanDateTime.appendChild(pDateTime);

    pDateTime.appendChild(document.createTextNode(getNowDateTime()));
    divCol.appendChild(divW100);
    divCol.appendChild(divColMessageText);
    divColMessageText.appendChild(pMessageText);
    pMessageText.appendChild(document.createTextNode(messageText));
    divCol.appendChild(br);
}


/**
 * @return {string}
 */
function getNowDateTime() {

    var newDate = new Date();
    var fullYear = newDate.getFullYear();
    var date = newDate.getDate();
    var month = (newDate.getMonth() + 1);

    if (date < 10)
        date = "0" + date;

    if (month < 10)
        month = "0" + month;

    var cur_day = fullYear + "-" + month + "-" + date;

    var hours = newDate.getHours()
    var minutes = newDate.getMinutes()
    var seconds = newDate.getSeconds();

    if (hours < 10)
        hours = "0" + hours;

    if (minutes < 10)
        minutes = "0" + minutes;

    if (seconds < 10)
        seconds = "0" + seconds;

    return cur_day + " " + hours + ":" + minutes + ":" + seconds;

}

function userCommand(messageChatArray) {
    var mca = messageChatArray;

    switch (mca[1]) {
        case 'rename' :
            renameUser(mca);
            break;
        case 'ban' :
            banUser(mca);
            break;
        case 'pardon' :
            pardonUser(mca);
            break;
        case 'moderator' :
            moderatorUser(mca)
            break;

    }

}

// команды для комнаты
function roomCommands(messageChatArray) {
    var mca = messageChatArray;

    switch (mca[1]) {
        case 'create' :
            createRoom(mca);
            break;
        case 'remove' :
            removeRoom(mca);
            break;
        case 'rename' :
            renameRoom(mca);
            break;
        case 'connect' :
            connectRoom(mca);
            break;
        case 'disconnect' :
            disconnectRoom(mca);
            break;
        case 'mute' :
            muteRoom(mca);
            break;
        case  'speak' :
            speakRoom(mca);
            break;
    }

}

function createRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        if (!mca[3]) {
            stompClient.send("/app/rooms/create", {}, JSON.stringify({
                'name': mca[2], 'privateRoom': 0, 'botRoom': 0
            }));
        } else if (mca[3] === '-c') {
            stompClient.send("/app/rooms/create", {}, JSON.stringify({
                'name': mca[2], 'privateRoom': 1, 'botRoom': 0
            }));
        } else if (mca[3] === '-b') {
            stompClient.send("/app/rooms/create", {}, JSON.stringify({
                'name': mca[2], 'privateRoom': 1, 'botRoom': 1
            }));
        }
    }

}

function removeRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        stompClient.send("/app/rooms/remove", {}, JSON.stringify({
            'name': mca[2]
        }));
    }

}

//todo ждет релизации
function renameRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        console.log('текущая комната переименована на: ' + mca[2])
    }

}

//todo ждет релизации
function connectRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        if (mca[3] === '-l') {
            if (mca[4]) {
                console.log('Добавлено пользователь с ником: ' + mca[4] + ' в комнату с названием: ' + mca[2])
            }
        }
    }
}

//todo ждет релизации
function disconnectRoom(messageChatArray) {
    var mca = messageChatArray;

    if (!mca[2]) {
        console.log('выйти из текущей комнаты')
    } else if (mca[2] && !mca[3]) {
        console.log('Вы вышли из комнаты: ' + mca[2])
    } else if (mca[2] && mca[3] === '-l' && messageChatArray[4]) {
        if (!mca[5]) {
            console.log('Вы выгнали пользователя: ' + mca[4] + ' из комнаты: ' + mca[2] + ' на 60 минут.')
        } else if (mca[5] === '-m' && mca[6]) {
            console.log('Вы выгнали пользователя: ' + mca[4] + ' из комнаты: ' + mca[2] + ' на ' + mca[6] + ' минут.')
        }
    }
}

//todo ждет релизации
function muteRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2] === '-l' && mca[3]) {
        if (!mca[4]) {
            console.log('Пользователь ' + mca[3] + ' замьючен на 10 минут в текущей комнате')
        } else if (mca[4] === '-m') {
            if (mca[5]) {
                console.log('Пользователь ' + mca[3] + ' замьючен на ' + mca[5] + ' минут в текущей комнате')
            }
        }
    }
}

//todo ждет релизации
function speakRoom(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2] === '-l' && mca[3]) {
        console.log('Пользователь ' + mca[3] + ' снова может говорить.')
    }
}

//todo ждет релизации
function renameUser(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2] && mca[3]) {
        console.log('Новый логин пользователя ' + mca[2] + ' - ' + mca[3]);
    }

}

//todo ждет релизации
function banUser(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        if (!mca[3]) {
            console.log('Пользователь ' + mca[2] + ' выгнан из всех комнат на 60 минут.')
        } else if (mca[3] === '-m' && mca[4]) {
            console.log('Пользователь ' + mca[2] + ' выгнан из всех комнат на ' + mca[4] + ' минут.')
        }
    }
}

//todo ждет релизации
function pardonUser(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        console.log('Пользователь ' + mca[2] + ' помилован во всех комнатах.')
    }
}

//todo ждет релизации
function moderatorUser(messageChatArray) {
    var mca = messageChatArray;

    if (mca[2]) {
        if (mca[3] === '-n') {
            console.log('Пользователь ' + mca[2] + ' назначен модератором.')
        } else if (mca[3] === '-d') {
            console.log('Пользователь ' + mca[2] + ' разжалован из модераторов.')
        }
    }
}


