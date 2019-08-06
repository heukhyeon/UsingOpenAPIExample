package kr.evalon.usingopenapi.api.unsplash

import java.lang.RuntimeException

class LimitOverException : RuntimeException("현재 사용 횟수가 초과되었습니다. 1시간뒤 다시 시도해주세요") {
}