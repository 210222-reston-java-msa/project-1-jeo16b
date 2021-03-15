function sendRequest() {
	console.log("send Request triggered");
    // save some variables here
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let type = document.getElementById('type').value;

    console.log(`Amount: ${amount}`);
    console.log(`Description: ${description}`);
    console.log(`Type: ${type}`);

    let userString = sessionStorage.getItem('currentUser');
    let currentUser = JSON.parse(userString);


    // building an obj literal with the reimbursement credentials
    let  reimbursement = {
        amount: amount,
        time: Date.now(),
        description: description,
        author: currentUser.id,
        type: type,
        status: 2
    }


    // begin some AJAX workflow....

    // 1. get the XMLHttpRequest Object i.e ... let xhr = ....
    let xhr = new XMLHttpRequest();

    // 2. xhr.onreadystatechange
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('newReimbursement', this.responseText);

            let newString = sessionStorage.getItem('newReimbursement');

            let newReimbursement = JSON.parse(userString);
        


            console.log(sessionStorage.getItem('newReimbursement'));
            window.location = "http://localhost:8080/project-1/EmployeeHome";
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to add new request");
        }
    }
    
    // 3. xhr.open("POST, "http:/localhost:8080/project-1/url)
    xhr.open("POST", "http://localhost:8080/project-1/newReimbursement");

    // 4. xhr.send();
    xhr.send(JSON.stringify(reimbursement));
}
