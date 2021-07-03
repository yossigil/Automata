package finite;

class Fluent {
    RE v = RE.c('a').or(RE.c('b')).then(RE.c('x')).star().then(RE.c('x'));
}
