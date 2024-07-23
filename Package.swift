// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MeetAgainShared",
    platforms: [
        .iOS(.v10)
    ],
    products: [
        .library(
            name: "MeetAgainShared",
            targets: ["MeetAgainShared"]),
    ],
    targets: [
        .binaryTarget(
            name: "MeetAgainShared",
            url: "https://github.com/kiiskristo/KMPShared/actions/runs/10052300748/artifacts/1728716262.zip",
            checksum: "0954c099ae18005b34690f81b2264c280678e4535293c145b7a5dc92f4bbe5e5"
        )
    ]
)