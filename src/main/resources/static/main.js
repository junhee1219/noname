async function postForm(url, data, msgElement) {
    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        const text = await res.text();
        msgElement.style.color = res.ok ? 'green' : 'red';
        msgElement.textContent = text;
    } catch (err) {
        msgElement.style.color = 'red';
        msgElement.textContent = err;
    }
}

const signupForm = document.getElementById('signupForm');
if (signupForm) {
    signupForm.addEventListener('submit', e => {
        e.preventDefault();
        const data = {
            username: signupForm.username.value,
            password: signupForm.password.value
        };
        postForm('/api/auth/signup', data, document.getElementById('signupMsg'));
    });
}

const loginForm = document.getElementById('loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', e => {
        e.preventDefault();
        const data = {
            username: loginForm.username.value,
            password: loginForm.password.value
        };
        postForm('/api/auth/login', data, document.getElementById('loginMsg'));
    });
}

async function fetchText(url, element) {
    try {
        const res = await fetch(url);
        element.textContent = await res.text();
    } catch (err) {
        element.textContent = err;
    }
}

const helloMsg = document.getElementById('helloMsg');
if (helloMsg) {
    fetchText('/hello', helloMsg);
}

const homeMsg = document.getElementById('homeMsg');
if (homeMsg) {
    fetchText('/', homeMsg);
}
